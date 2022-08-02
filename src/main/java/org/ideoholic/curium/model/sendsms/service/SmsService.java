package org.ideoholic.curium.model.sendsms.service;

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

import org.ideoholic.curium.model.academicyear.dao.YearDAO;
import org.ideoholic.curium.model.academicyear.dto.Currentacademicyear;
import org.ideoholic.curium.model.employee.dao.EmployeeDAO;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendsms.dao.SmsDAO;
import org.ideoholic.curium.util.DataUtil;
import org.ideoholic.curium.util.Session;

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
				querySub = querySub + "parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
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
					querySub = querySub + "parents.Student.classstudying like '"+classStudying+"' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.Student.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
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
						resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebodystaff")),"staffall");
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
		logger.info(templateType+": URL "+POST_URL);
		System.out.println(templateType+": URL "+POST_URL);
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
		}}}
		catch (Exception e)
		{
		logger.info("Error SMS "+e);
		}
		return responseCode;
	}
	
}
