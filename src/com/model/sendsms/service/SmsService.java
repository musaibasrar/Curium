package com.model.sendsms.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.parents.dto.Parents;
import com.model.sendsms.dao.SmsDAO;
import com.util.DataUtil;

public class SmsService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
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
				querySub = querySub + "parents.Student.archive=0";
			}else{
				if (!addClass.equalsIgnoreCase("")) {

					conClassStudying = addClass+"--" +"%";

				}
				if (!addSec.equalsIgnoreCase("")) {
					conClassStudying = addClass;
					conClassStudying = conClassStudying+addSec;
				}
				
				String classStudying = DataUtil.emptyString(conClassStudying);
				
				if(!classStudying.equalsIgnoreCase("")){
					querySub = querySub + "parents.Student.classstudying like '"+classStudying+"' AND parents.Student.archive=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute("branchid").toString());
				}	
			}
			
			queryMain = queryMain+querySub;

			double totalNumbers = new SmsDAO().countNumbers(queryMain);
			String resultSMS=null;
			int iterations = (int) Math.ceil(totalNumbers/100);
			
			System.out.println("main query:"+queryMain);
			
			for(int i=0;i<iterations;i++){
				List<Parents> parentsContacts = new SmsDAO().readListOfObjectsPaginationALL(offset, noOfRecords, queryMain);

				String numbers = null;
					StringBuilder sbN = new StringBuilder();

					if(!parentsContacts.isEmpty()){
						for (Parents parents : parentsContacts) {
							sbN.append(parents.getContactnumber());
							sbN.append(",");
						}
						numbers=sbN.toString();
						numbers = numbers.substring(0, numbers.length()-1);
						System.out.println("Numbers are *** "+numbers);
						resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebody")));
					}
					
				offset = offset+100;
			}
			if(resultSMS!=null && resultSMS.contains("success")){
				result = true;
			}
		}
		
        return result;
		
	}

	
	public boolean sendNumbersSMS() {
		
		boolean result=false;
		String numbers = DataUtil.emptyString(request.getParameter("numbers"));
		String resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebodynumbers")));
		if(resultSMS!=null && resultSMS.contains("success")){
			result = true;
		}
		return result;
		
	}

	public boolean sendStaffSMS() {
		
		boolean result=false;
		String resultSMS = null;		
		List<Teacher> teacherContacts = new EmployeeDAO().readListOfObjects(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		String numbers = null;
			StringBuilder sbN = new StringBuilder();

			if(!teacherContacts.isEmpty()){
				for (Teacher teacher : teacherContacts) {
					sbN.append(teacher.getContactnumber());
					sbN.append(",");
				}
				numbers=sbN.toString();
				numbers = numbers.substring(0, numbers.length()-1);
				System.out.println("Numbers are *** "+numbers);
				resultSMS = sendSMS(numbers,DataUtil.emptyString(request.getParameter("messagebodystaff")));
			}
		
		if(resultSMS!=null && resultSMS.contains("success")){
			result = true;
		}
		return result;
	}
	
	public String sendSMS(String numbers, String message) {
		String sResult1="";
		
		try 
		{
			Properties properties = new Properties();
	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
	        properties.load(inputStream);
	        String smsuser = properties.getProperty("smsuser");
	        String smspassword = properties.getProperty("smspassword");
	        String smssender = properties.getProperty("smssender");
	        
		// Construct data
		String phonenumbers=numbers;
		String data="user=" + URLEncoder.encode(smsuser, "UTF-8");
		data +="&password=" + URLEncoder.encode(smspassword, "UTF-8");
		data +="&message=" + URLEncoder.encode(message, "UTF-8");
		data +="&sender=" + URLEncoder.encode(smssender, "UTF-8");
		data +="&mobile=" + URLEncoder.encode(phonenumbers, "UTF-8");
		data +="&type=" + URLEncoder.encode("3", "UTF-8");
		// Send data
		URL url = new URL("http://login.bulksmsgateway.in/sendmessage.php?"+data);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data);
		wr.flush();
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		
		while ((line = rd.readLine()) != null) 
		{
		// Process line...
		sResult1=sResult1+line+" ";
		}
		wr.close();
		rd.close();
		return sResult1;
		} 
		catch (Exception e)
		{
		System.out.println("Error SMS "+e);
		return "Error "+e;
		}
	
	}
	
}
