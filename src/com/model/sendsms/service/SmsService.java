package com.model.sendsms.service;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.model.account.dao.AccountDAO;
import com.model.account.dto.Financialaccountingyear;
import com.model.employee.dto.Teacher;
import com.model.feescategory.dao.feesCategoryDAO;
import com.model.feescategory.dto.Feescategory;
import com.model.feescollection.dto.StudentFeesReport;
import com.model.parents.dto.Parents;
import com.model.sendsms.dao.SmsDAO;
import com.model.student.dao.studentDetailsDAO;
import com.model.student.dto.Studentfeesstructure;
import com.util.DataUtil;
import com.util.DateUtil;

public class SmsService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    private String BRANCHID = "branchid";
	    
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
					
			queryMain = queryMain+querySub+ " AND teacher.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString());

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
		return responseCode;
	}


	public void getDueContribution() {
		
		
		//Get Students
		
		List<Parents> searchStudentList = new ArrayList<Parents>();
		
		if(httpSession.getAttribute(BRANCHID)!=null){
		
		String queryMain = "From Parents as parents where ";
		String querySub = "";
			
		querySub = querySub + " parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid="+Integer.parseInt(httpSession.getAttribute(BRANCHID).toString())+" order by parents.Student.admissionnumber ASC";

		if(!"".equalsIgnoreCase(querySub)) {
			queryMain = queryMain + querySub;
			searchStudentList = new studentDetailsDAO().getStudentsList(queryMain);
		}
		
	}
		//End Students
		
		Financialaccountingyear financialYear = new AccountDAO().getCurrentFinancialYear(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
		
		List<String> months = new ArrayList<String>();
		String financialEndDate = DateUtil.dateParserddMMYYYY(new Date());
		String startDate = DateUtil.dateParserddMMYYYY(financialYear.getFinancialstartdate());
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM");
	    YearMonth endMonth = YearMonth.parse(financialEndDate, dateFormatter);
	    for (YearMonth month = YearMonth.parse(startDate, dateFormatter);! month.isAfter(endMonth); month = month.plusMonths(1)) {
	    	months.add(month.format(monthFormatter));
	    }
	    
		List<Feescategory> feesCategory = new feesCategoryDAO().getFeesCatgoryByName(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()),months);
		List<Integer> feesId = new ArrayList<Integer>();
		
		for (Feescategory feescategory2 : feesCategory) {
			feesId.add(feescategory2.getIdfeescategory());
		}
          
		
			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();
			
			for (Parents parents : searchStudentList) {
				
				StudentFeesReport studentFeesReport = new StudentFeesReport();
				
				long id = parents.getStudent().getSid();
				List<Studentfeesstructure> feesstructure = new studentDetailsDAO().getStudentFeesStructureByFeesCategory(id, httpSession.getAttribute("currentAcademicYear").toString(),feesId);
				
				studentFeesReport.setStudent(parents.getStudent());
				studentFeesReport.setStudentFeesStructure(feesstructure);
				
				studentFeesReportList.add(studentFeesReport);
			}
		
			httpSession.setAttribute("studentfeesreportlist", studentFeesReportList);
		
	  }


	public boolean dueContributionSendSMS() {
		
		boolean result=false;
		int noOfRecords = 100;
		int offset=0;
		
		if(httpSession.getAttribute(BRANCHID)!=null){
			int resultSMS=0; 
			String[] sid = request.getParameterValues("studentIDs");
			
			List<StudentFeesReport> studentFeesReportList = new ArrayList<StudentFeesReport>();
			
			studentFeesReportList = (List<StudentFeesReport>) httpSession.getAttribute("studentfeesreportlist");
			
			for (String studentId : sid) {
				
				for (StudentFeesReport studentFeesReport : studentFeesReportList) {
					
					if(studentFeesReport.getStudent().getSid() == Integer.parseInt(studentId)) {
						String mainMessage = "";
						long totalFess = 0l;
						
						
						for (Studentfeesstructure studentFeesStructure : studentFeesReport.getStudentFeesStructure()) {
							long feesPaid  = studentFeesStructure.getFeesamount()-studentFeesStructure.getFeespaid()-studentFeesStructure.getConcession()-studentFeesStructure.getWaiveoff();
							
							if(feesPaid>0) {
								mainMessage =  mainMessage+studentFeesStructure.getFeescategory().getFeescategoryname()+" = Rs. "+feesPaid+" %0D%0A\n";
								totalFess = totalFess+feesPaid;
							}
							
						}
						
						String message = "Donation Due Reminder%0D%0A\n"
								+ "\n"
								+ "Assalamalikum Rahmatullah hi wa Barkatahu%0D%0A \n"
								+ "\n"
								+ "Your donations for Masjid  has bot been recived for the month of%0D%0A \n"
								+ mainMessage+" %0D%0A\n"
								+ "Total  =  Rs. "+totalFess+" %0D%0A\n"
								+ "Kindly pay your donations at the earliest possible.%0D%0A \n"
								+ "\n"
								+ "(Please ignore this message If you have already Paid)%0D%0A\n"
								+ "\n"
								+ "On behalf of Masjid Ali RA Management Committee,  We extend our sincere thanks Pray to Allah Subhanahu wa Tala to reward you profusely in this world and in hereafter. Ameen Summa ameen";
						
						sendSMSDueRemiders(studentFeesReport.getStudent().getClassadmittedin(),message);
					}
					
				}
			}
			
			

			String numbers = null;
			StringBuilder sbN = new StringBuilder();

			/*
			 * if(contactNumber != null){ for (String contactNo : contactNumber) {
			 * sbN.append(contactNo); sbN.append(","); } numbers=sbN.toString(); numbers =
			 * numbers.substring(0, numbers.length()-1);
			 * logger.info("Numbers are *** "+numbers); resultSMS =
			 * sendSMSRemiders(numbers,"duecontribution"); }
			 */
			
			offset = offset+100;
		
			
			if(resultSMS==200){
				result = true;
			}
		}
		
        return result;
		
	}


	private int sendSMSDueRemiders(String numbers, String message) {
		int responseCode = 0;
		try 
		{
			Properties properties = new Properties();
	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("util.properties");
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
	
	
	public int sendSMSRemiders(String numbers, String messageCode) {
		int responseCode = 0;
		try 
		{
			Properties properties = new Properties();
	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("util.properties");
	        properties.load(inputStream);
	        String smsuser = properties.getProperty("smsuser");
	        String smssender = properties.getProperty("smssender");
	        String apikey = properties.getProperty("apikey");
	        String message = properties.getProperty(messageCode);
	        logger.info("Message :: " + message);
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
