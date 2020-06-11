package org.ideoholic.email.service;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import com.model.parents.dto.Parents;
import com.model.sendemail.dao.EmailDAO;
import com.util.DataUtil;

public class EmailServiceImpl implements EmailService {
	
public String sendAllEmail(String branchId,String addClass,String addSec,String subject,String messageBody) {
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
						result = sendEmail(emails,DataUtil.emptyString(subject),DataUtil.emptyString(messageBody)) != null;
					}
					
				offset = offset+100;
			}
		}
		
		sb.append("}");
		
        return sb.toString();
		
	}

private String sendEmail(String emails, String subject,String message) {
	StringBuffer sb = new StringBuffer();
	sb.append("{");
	boolean result = false;
	try {
		Properties properties = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Backuplocation.properties");
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
	sb.append("}");
	return sb.toString();
}
}
