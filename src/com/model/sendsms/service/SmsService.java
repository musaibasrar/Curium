package com.model.sendsms.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.parents.dto.Parents;
import com.model.sendsms.dao.SmsDAO;
import com.util.DataUtil;
import com.util.Session;

public class SmsService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    
	private static DecimalFormat df2 = new DecimalFormat(".##");
	 private static final Logger logger = LogManager.getLogger(SmsService.class);
	
	public SmsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}


	public boolean sendAllSMS() {
		
		boolean result=false;
		int noOfRecords = 100;
		int offset=0;
		
		if(httpSession.getAttribute("branchid")!=null){
			String queryMain ="From Parents as parents where ";
			String querySub = "";
			String addClass = request.getParameter("addclass");
			String addSec = request.getParameter("addsec");
			String conClassStudying = "";
			
			if(addClass.contains("ALL")){
				querySub = querySub + "parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
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
					querySub = querySub + "parents.Student.classstudying like '"+classStudying+"' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
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
						
						String SMSTempType = request.getParameter("messagebody");
						String message = request.getParameter(SMSTempType+"var1")+":"+request.getParameter(SMSTempType+"var2")+":"+request.getParameter(SMSTempType+"var3")+":"+request.getParameter(SMSTempType+"var4");
						
						resultSMS = sendSMS(numbers,message,SMSTempType);
					}
					
				offset = offset+100;
			}
			if(resultSMS==200){
				result = true;
			}
		}
		
        return result;
		
	}

	
	public boolean sendNumbersSMS() {
		
		boolean result=false;
		String numbers = DataUtil.emptyString(request.getParameter("numbers"));
		int resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebodynumbers")),"all");
		if(resultSMS==200){
			result = true;
		}
		return result;
		
	}

	public boolean sendStaffSMS() {
		
		boolean result=false;
		int noOfRecords = 100;
		int offset=0;
		
		if(httpSession.getAttribute("branchid")!=null){
			String queryMain ="From Teacher as teacher where ";
			String querySub = "";
			String department = request.getParameter("department");
			
			if (!department.equalsIgnoreCase("")) {
				
					if(department.contains("ALL")){
							querySub = querySub + "teacher.currentemployee=1";
					}else{
							querySub = querySub + "teacher.department = '"+department+"' AND teacher.currentemployee=1";
					}
					
			queryMain = queryMain+querySub+ " AND teacher.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());

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
						resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebodystaff")),"all");
					}
					
				offset = offset+100;
			}
			if(resultSMS==200){
				result = true;
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
	        	
	        String smssender = properties.getProperty("smssender");
	        String apikey = properties.getProperty("apikey");
	        String smsURL = properties.getProperty("smsurl");
			String channel = properties.getProperty("channel");
			String route = properties.getProperty("route");
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
		
			String data = URLEncoder.encode("APIKey", "UTF-8") + "=" + URLEncoder.encode(apikey, "UTF-8");
			data += "&" + URLEncoder.encode("senderid", "UTF-8") + "=" + URLEncoder.encode(smssender, "UTF-8");
			data += "&" + URLEncoder.encode("channel", "UTF-8") + "=" + URLEncoder.encode(channel, "UTF-8");
			data += "&" + URLEncoder.encode("DCS", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
			data += "&" + URLEncoder.encode("flashsms", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8");
			data += "&" + URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(numbers, "UTF-8");
			data += "&" + URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(templatemessage, "UTF-8");
			data += "&" + URLEncoder.encode("route", "UTF-8") + "=" + URLEncoder.encode(route, "UTF-8");
			data += "&" + URLEncoder.encode("DLTTemplateId", "UTF-8") + "=" + URLEncoder.encode(templateid, "UTF-8");
			data += "&" + URLEncoder.encode("PEID", "UTF-8") + "=" + URLEncoder.encode(peid, "UTF-8");
			String rsp = "";
			String url1 = smsURL;
			url1 += "?" + data;
			URL url = new URL(url1);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			  while ((line = reader.readLine()) != null) {
				  		sb = sb.append(line);
			  		}
			  			rsp = sb.toString();
			  			
			  			responseCode = connection.getResponseCode();
			  			logger.info("POST Response Code :: " + responseCode);

			  			if (responseCode == HttpURLConnection.HTTP_OK) { //success
			  				BufferedReader in = new BufferedReader(new InputStreamReader(
			  						connection.getInputStream()));
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
			  			}
	        }	
			  			
		}
		catch (Exception e)
		{
		logger.info("Error SMS "+e);
		}
		return responseCode;
	}
	
}
