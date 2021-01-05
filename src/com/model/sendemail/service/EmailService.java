package com.model.sendemail.service;

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

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import com.model.academicyear.dao.YearDAO;
import com.model.academicyear.dto.Currentacademicyear;
import com.model.employee.dao.EmployeeDAO;
import com.model.employee.dto.Teacher;
import com.model.parents.dto.Parents;
import com.model.sendemail.dao.EmailDAO;
import com.model.sendsms.dao.SmsDAO;
import com.util.DataUtil;

public class EmailService {
	
	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession httpSession;
	    
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	public EmailService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
        this.response = response;
        this.httpSession = request.getSession();
	}


	public boolean sendAllEmail() {
		
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
			
			double totalEmails = new EmailDAO().countEmails(queryMain);
			int iterations = (int) Math.ceil(totalEmails/100);
			
			System.out.println("main query:"+queryMain);
			
			for(int i=0;i<iterations;i++){
				List<Parents> parentsEmails = new EmailDAO().readListOfObjectsPaginationALL(offset, noOfRecords, queryMain);

				String emails = null;
					StringBuilder sbN = new StringBuilder();

					if(!parentsEmails.isEmpty()){
						for (Parents parents : parentsEmails) {
							sbN.append(parents.getEmail());
							sbN.append(",");
						}
						emails=sbN.toString();
						emails = emails.substring(0, emails.length()-1);
						System.out.println("emails are *** "+emails);
						result = sendEmail(emails,DataUtil.emptyString(request.getParameter("subject")),DataUtil.emptyString(request.getParameter("messagebody")));
					}
					
				offset = offset+100;
			}
		}
		
		
		
        return result;
		
	}

	
	private boolean sendEmail(String emails, String subject,
			String message) {
		boolean result = false;
		try {
			Properties properties = new Properties();
	        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Util.properties");
	        properties.load(inputStream);
	        String smtpHost = properties.getProperty("HOSTNAME");
			String smtpPort = properties.getProperty("SMTPPORT");
			String userName = properties.getProperty("USERNAME");
			String password = properties.getProperty("PASSWORD");
			String fromAdd = properties.getProperty("FROMADDRESS");
			
			
			Email email = new SimpleEmail();
			email.setHostName(smtpHost);
			int smtp = Integer.parseInt(smtpPort);
			email.setSmtpPort(smtp);
			email.setAuthenticator(new DefaultAuthenticator(userName, password));
			email.setSSLOnConnect(true);
			email.setFrom(fromAdd);
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(emails);
						
			email.send();
			result = true;
		} catch (Exception e) {
			System.out.println(""+e);
		}
		
		return result;
	}
	
}
