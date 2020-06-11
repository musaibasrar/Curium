package org.ideoholic.sms.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.employee.dto.Teacher;
import com.model.parents.dto.Parents;
import com.model.sendsms.dao.SmsDAO;
import com.util.DataUtil;

public class SmsServiceImpl implements SmsService {
	
	private static final Logger logger = LogManager.getLogger(SmsService.class);

public String sendAllSMS(String branchId,String addClass,String addSec, String messageBody) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
		
		boolean result=false;
		int noOfRecords = 100;
		int offset=0;
		
		if(branchId!=null){
			String queryMain ="From Parents as parents where ";
			String querySub = "";
			String conClassStudying = "";
			
			if(addClass.contains("ALL")){
				querySub = querySub + "parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
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
					querySub = querySub + "parents.Student.classstudying like '"+classStudying+"' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(branchId.toString());
				}	
			}
			
			queryMain = queryMain+querySub;

			double totalNumbers = new SmsDAO().countNumbers(queryMain);
			int resultSMS=0;
			int iterations = (int) Math.ceil(totalNumbers/100);
			
			logger.info("main query:"+queryMain);
			
			for(int i=0;i<iterations;i++){
				List<Object> pContacts = new SmsDAO().readListOfObjectsPaginationALL(offset, noOfRecords, queryMain);

				//List<Parents> parentsContacts = (List<Parents>) Parents.class.cast(pContacts); 
				
				String numbers = null;
					StringBuilder sbN = new StringBuilder();

					if(!pContacts.isEmpty()){
						for (Object parents : pContacts) {
							Parents par = Parents.class.cast(parents);
							sbN.append(par.getContactnumber());
							sbN.append(",");
						}
						numbers=sbN.toString();
						numbers = numbers.substring(0, numbers.length()-1);
						logger.info("Numbers are *** "+numbers);
					}
					
				offset = offset+100;
			}
			if(resultSMS==200){
				result = true;
			}
		}
		
		sb.append("}");
        return sb.toString();
		
	}

public String sendNumbersSMS(String numbers,int resultSMS) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result=false;
	if(resultSMS==200){
		result = true;
	}
	sb.append("}");
	return sb.toString();
	
}

public String sendStaffSMS(String branchId,String department, String messageBodyStaff) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	
	boolean result=false;
	int noOfRecords = 100;
	int offset=0;
	
	if(branchId!=null){
		String queryMain ="From Teacher as teacher where ";
		String querySub = "";
		
		if (!department.equalsIgnoreCase("")) {
			
				if(department.contains("ALL")){
						querySub = querySub + "teacher.currentemployee=1";
				}else{
						querySub = querySub + "teacher.department = '"+department+"' AND teacher.currentemployee=1";
				}
				
		queryMain = queryMain+querySub+ " AND teacher.branchid="+Integer.parseInt(branchId.toString());

		double totalNumbers = new SmsDAO().countNumbers(queryMain);
		int resultSMS=0;
		int iterations = (int) Math.ceil(totalNumbers/100);
		
		logger.info("main query:"+queryMain);
		
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
					logger.info("Numbers are *** "+numbers);
				}
				
			offset = offset+100;
		}
		if(resultSMS==200){
			result = true;
		}
	}
	}
	sb.append("}");
    return sb.toString();
}

public String sendSMS(String numbers, String message) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	int responseCode = 0;
	try 
	{
		Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
        properties.load(inputStream);
        String smsuser = properties.getProperty("smsuser");
        String smssender = properties.getProperty("smssender");
        String apikey = properties.getProperty("apikey");
        
      
	// Construct data
	String phonenumbers=numbers;
	String data="username=" + URLEncoder.encode(smsuser, "UTF-8");
	data +="&message=" + URLEncoder.encode(message, "UTF-8");
	data +="&sendername=" + URLEncoder.encode(smssender, "UTF-8");
	data +="&smstype=" + "TRANS";
	data +="&numbers=" + URLEncoder.encode(phonenumbers, "UTF-8");
	data +="&apikey=" + apikey;
	// Send data
	
	String POST_URL = "http://sms.bulksmsind.in/sendSMS?"+data;
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
	logger.info("POST Response Code :: " + responseCode);

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
		logger.info(response.toString());
	} else {
		logger.info("POST request not worked");
	}}
	catch (Exception e)
	{
	logger.info("Error SMS "+e);
	}
	sb.append("}");
	return sb.toString();
}
}
