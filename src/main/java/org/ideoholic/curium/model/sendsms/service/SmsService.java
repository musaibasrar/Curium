package org.ideoholic.curium.model.sendsms.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendsms.dao.SmsDAO;
import org.ideoholic.curium.model.sendsms.dto.SMSResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.SMSReportResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendsms.dao.SmsDAO;
import org.ideoholic.curium.model.sendsms.dto.SMSResponseDto;
import org.ideoholic.curium.model.sendsms.dto.SendSMSDto;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.SMSReportResponse;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsService {


	public ResultResponse sendAllSMS(SendSMSDto dto, String branchId) {

		int noOfRecords = 100;
		int offset=0;
		
		if(branchId!=null){
		int maxRetries = 3;
		int attempts = 0;
			String queryMain ="From Parents as parents where ";
			String querySub = "";
			String addClass =dto.getAddClass();
			String addSec = dto.getAddSec();
			String conClassStudying = "";
			
			if(addClass.contains("ALL")){
				querySub = querySub + "parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchId);
			}else{
				if (!addClass.equalsIgnoreCase("")) {

					conClassStudying = addClass+"--" +"%";

				}
				if (!addSec.equalsIgnoreCase("")) {
					conClassStudying = addClass;
					conClassStudying = conClassStudying+"--"+addSec+"%";
				}
				
				String classStudying = DataUtil.emptyString(conClassStudying);
				
				if(!classStudying.equalsIgnoreCase("")){
					querySub = querySub + "parents.Student.classstudying like '"+classStudying+"' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(branchId);
				}	
			}
			
			queryMain = queryMain+querySub;

			double totalNumbers = new SmsDAO().countNumbers(queryMain);
			int resultSMS=0;
			int iterations = (int) Math.ceil(totalNumbers/100);
			
			log.info("main query:"+queryMain);
			
			for(int i=0;i<iterations;i++){
				List<Object> pContacts = new SmsDAO().readListOfObjectsPaginationALL(offset, noOfRecords, queryMain);

				//List<Parents> parentsContacts = (List<Parents>) Parents.class.cast(pContacts); 
				
				String numbers = null;
					StringBuilder sbN = new StringBuilder();

					if(!pContacts.isEmpty()){
						for (Object parents : pContacts) {
							Parents par = Parents.class.cast(parents);
							
							String phoneNo = par.getContactnumber();
							if(phoneNo!=null && !phoneNo.isEmpty()) {
								char[] contactNo = phoneNo.toCharArray();
								
								if(contactNo.length == 10) {
									sbN.append(par.getContactnumber());
									sbN.append(",");
								}
							}
						}
						numbers=sbN.toString();
						numbers = numbers.substring(0, numbers.length()-1);
						log.info("Numbers are *** "+numbers);
						
						String SMSTempType = dto.getSmsTempType();
						String message = dto.getMessage();
						
						while (attempts < maxRetries) {
							resultSMS = sendSMS(numbers,message,SMSTempType);
						    
						    if (resultSMS == 200) {
						        break; // success, exit loop
						    }
						    
						    attempts++; // retry if not successful
						}
					}
					
				offset = offset+100;
			}
			if(resultSMS==200){
				 ResultResponse.builder().success(true).build();
			}
		}
		
        return ResultResponse.builder().build();
		
	}

	
	public ResultResponse sendNumbersSMS(SendSMSDto dto) {
		ResultResponse result = ResultResponse.builder().build();

		String numbers = DataUtil.emptyString(dto.getNumbers());
		int resultSMS = sendSMS(numbers,DataUtil.emptyString(dto.getMessageBodyNumbers()),"all");
		if(resultSMS==200){
			result.setSuccess(true);
		}
		return result;
	}

	public ResultResponse sendStaffSMS(SendSMSDto dto, String branchId) {
		ResultResponse result = ResultResponse.builder().build();

		int noOfRecords = 100;
		int offset=0;
		
		if(branchId!=null){
			String queryMain ="From Teacher as teacher where ";
			String querySub = "";
			String department = dto.getDepartment();
			
			if (!department.equalsIgnoreCase("")) {
				
					if(department.contains("ALL")){
							querySub = querySub + "teacher.currentemployee=1";
					}else{
							querySub = querySub + "teacher.department = '"+department+"' AND teacher.currentemployee=1";
					}
					
			queryMain = queryMain+querySub+ " AND teacher.branchid="+Integer.parseInt(branchId);

			double totalNumbers = new SmsDAO().countNumbers(queryMain);
			int resultSMS=0;
			int iterations = (int) Math.ceil(totalNumbers/100);
			
			log.info("main query:"+queryMain);
			
			for(int i=0;i<iterations;i++){
				List<Object> teacherContacts = new SmsDAO().readListOfObjectsPaginationALL(offset, noOfRecords, queryMain);
				
				//List<Teacher> teachersContact = (List<Teacher>) Teacher.class.cast(teacherContacts); 
						
				String numbers = null;
					StringBuilder sbN = new StringBuilder();

					if(!teacherContacts.isEmpty()){
						for (Object teacher : teacherContacts) {
							Teacher teach = Teacher.class.cast(teacher);
							sbN.append(teach.getContactnumber());
							sbN.append(",");
						}
						numbers=sbN.toString();
						numbers = numbers.substring(0, numbers.length()-1);
						log.info("Numbers are *** "+numbers);
						resultSMS = sendSMS(numbers,DataUtil.emptyString(dto.getMessageBodyStaff()),"staffall");
					}
					
				offset = offset+100;
			}
			if(resultSMS==200){
				result.setSuccess(true);
			}
		}
		}
		
        return result;
	}
	
	public int sendSMS(String numbers, String message, String templateType) {
		int responseCode = 0;
		try 
		{
			Properties properties = new Properties();
	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
	        properties.load(inputStream);
	        
	        String sendsms = properties.getProperty(templateType+"sendsms");
	        
	        if("yes".equalsIgnoreCase(sendsms)) {

	        String smsuser = properties.getProperty("smsuser");
	        String smssender = properties.getProperty("smssender");
	        String apikey = properties.getProperty("apikey");
	        String peid = properties.getProperty("peid");
	        String templateid = properties.getProperty(templateType+"templateid");
	        String templatemessage = properties.getProperty(templateType+"templatemessage");
	        String[] messageSeq = message.split(":");
	        String var1 = "";
	        String var2 = "";
	        String var3 = "";
	        String var4 = "";
	        
	        int messageIterations = messageSeq.length;
	        
	        
	        switch (messageIterations) {
			case 1:
				var1=messageSeq[0];
				break;
			case 2:
				var1=messageSeq[0];
				var2=messageSeq[1];
				break;
			case 3:
				var1=messageSeq[0];
				var2=messageSeq[1];
				var3=messageSeq[2];
				break;
			case 4:
				var1=messageSeq[0];
				var2=messageSeq[1];
				var3=messageSeq[2];
				var4=messageSeq[3];
				break;

			default:
				break;
			}
	        
	        templatemessage = templatemessage.replace("var1", var1);
	        templatemessage = templatemessage.replace("var2", var2);
	        templatemessage = templatemessage.replace("var3", var3);
	        templatemessage = templatemessage.replace("var4", var4);
		// Construct data
		String phonenumbers=numbers;
		String data="username=" + URLEncoder.encode(smsuser, "UTF-8");
		data +="&message=" + URLEncoder.encode(templatemessage, "UTF-8");
		data +="&sendername=" + URLEncoder.encode(smssender, "UTF-8");
		data +="&smstype=" + "TRANS";
		data +="&numbers=" + URLEncoder.encode(phonenumbers, "UTF-8");
		data +="&apikey=" + apikey;
		data +="&peid=" + peid;
		data +="&templateid=" + templateid;
		// Send data
		
		String POST_URL = "http://sms.bulksmsind.in/sendSMS?"+data;
		log.info(templateType+": URL "+POST_URL);
		log.debug(templateType+": URL "+POST_URL);
        URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write("CURIUM".getBytes());
		os.flush();
		os.close();
		// For POST only - END

		responseCode = con.getResponseCode();
		log.info("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			log.info(response.toString());
		} else {
			log.error("POST request not worked");
		}}}
		catch (Exception e)
		{
		log.error("Error SMS "+e);
		}
		return responseCode;
	}
	
	public ResultResponse sendSMSFeesDueReminder(SendSMSDto dto) {
		int resultSMS=0;

		List<StudentFeesReport> studentFeesReportList =dto.getStudentFeesReportList();
		String[] studentIds = dto.getStudentIds();
		String numbers = null;
					StringBuilder sbN = new StringBuilder();

					if(!studentFeesReportList.isEmpty()){
						for (StudentFeesReport studentFeesReport : studentFeesReportList) {
							if (Arrays.asList(studentIds).contains(studentFeesReport.getParents().getStudent().getSid().toString())) {
								String phoneNo = studentFeesReport.getParents().getContactnumber();
								if(phoneNo!=null && phoneNo.length() == 10) {
									
										long dueAmount = 0l;
										for (Studentfeesstructure studentFeesStructure : studentFeesReport.getStudentFeesStructure()) {
											dueAmount =dueAmount+(studentFeesStructure.getFeesamount()-studentFeesStructure.getFeespaid() - studentFeesStructure.getConcession() - studentFeesStructure.getWaiveoff());	
										}
										
										String SMSTempType = "feesreminderwithdueamount";
										String message = "Rs."+dueAmount+" ("+studentFeesReport.getParents().getStudent().getName().substring(0, Math.min(18, studentFeesReport.getParents().getStudent().getName().length()))+") : "+dto.getMessage()+"";
										
										int attempts = 0;
								        while (attempts < 1) {
								            resultSMS = sendSMS(phoneNo, message, SMSTempType);
								            if (resultSMS == 200) break;
								            attempts++;
								        }
								}
							}
							
						}
					}
					
			if(resultSMS==200){
				return ResultResponse.builder().success(true).build();

			}
			
			return ResultResponse.builder().build();
		}


	public SMSResponseDto SMSDeliveryReport() {

		SMSResponseDto result = new SMSResponseDto();

		int responseCode = 0;
		List<SMSReportResponse> reportResponses = null;
		 try {
			 
			 
			 	Properties properties = new Properties();
		        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
		        properties.load(inputStream);
		        
		        String smsuser = properties.getProperty("smsuser");
		        String smssender = properties.getProperty("smssender");
		        String apikey = properties.getProperty("apikey");
		        String peid = properties.getProperty("peid");
			 
	            // Get the current date and two days earlier date
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Calendar calendar = Calendar.getInstance();
	            String toDate = dateFormat.format(calendar.getTime());
	            
	            calendar.add(Calendar.DAY_OF_YEAR, -1);
	            String fromDate = dateFormat.format(calendar.getTime());
	            
	            // Replace parameters in the URL
	            String apiUrl = String.format("http://sms.bulksmsind.in/getDLRReport?username=%s&apikey=%s&from=%s&to=%s&sendername=%s", 
	            		smsuser, apikey, fromDate, toDate, smssender);

	            // Create URL object
	            URL url = new URL(apiUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // Set request method
	            connection.setRequestMethod("GET");

	            // Get the response code
	            responseCode = connection.getResponseCode();
	            System.out.println("Response Code: " + responseCode);
	            
	            // Read the response
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                ObjectMapper objectMapper = new ObjectMapper();
	                reportResponses = objectMapper.readValue(in, new TypeReference<List<SMSReportResponse>>() {});
					/*
					 * String inputLine; StringBuilder content = new StringBuilder();
					 * 
					 * while ((inputLine = in.readLine()) != null) { content.append(inputLine); }
					 * // Print the response
	                System.out.println("Response Content: " + content.toString());
					 */

	                // Close connections
	                in.close();
	                connection.disconnect();

	                
	                
	            } else {
	                System.out.println("GET request failed");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 
		 if(responseCode==200) {
			 result.setSuccess(true);
			 result.setSmsDeliveryReport(reportResponses);

		 }
		 
		 return result;
	}
	
}
