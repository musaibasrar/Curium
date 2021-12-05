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
						resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebody")));
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
		int resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebodynumbers")));
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
						resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebodystaff")));
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
	
	public int sendSMS(String numbers, String message) {
		int responseCode = 0;
		try 
		{
			Properties properties = new Properties();
	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
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
		return responseCode;
	}
	
}
