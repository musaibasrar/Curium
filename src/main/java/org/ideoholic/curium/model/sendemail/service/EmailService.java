package org.ideoholic.curium.model.sendemail.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendemail.dao.EmailDAO;
import org.ideoholic.curium.model.sendemail.dto.SendAllEmailDto;
import org.ideoholic.curium.util.DataUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
@Slf4j
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


	public ResultResponse sendAllEmail(SendAllEmailDto dto, String branchId) {
		ResultResponse result = ResultResponse.builder().build();

		int noOfRecords = 100;
		int offset=0;

		try {
			if (branchId != null) {

				String queryMain = "From Parents as parents where ";
				String querySub = "";
				String addClass = dto.getAddClass();
				String addSec = dto.getAddSec();
				String conClassStudying = "";

				if (addClass.contains("ALL")) {
					querySub = querySub + "parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0";
				} else {
					if (!addClass.equalsIgnoreCase("")) {

						conClassStudying = addClass + "--" + "%";

					}
					if (!addSec.equalsIgnoreCase("")) {
						conClassStudying = addClass;
						conClassStudying = conClassStudying + "--" + addSec + "%";
					}

					String classStudying = DataUtil.emptyString(conClassStudying);

					if (!classStudying.equalsIgnoreCase("")) {
						querySub = querySub + "parents.Student.classstudying like '" + classStudying + "' AND parents.Student.archive=0 and parents.Student.passedout=0 AND parents.Student.droppedout=0 and parents.Student.leftout=0 AND parents.branchid=" + Integer.parseInt(branchId);
					}
				}

				queryMain = queryMain + querySub;

				double totalEmails = new EmailDAO().countEmails(queryMain);
				int iterations = (int) Math.ceil(totalEmails / 100);

				log.error("main query:" + queryMain);

				for (int i = 0; i < iterations; i++) {
					List<Parents> parentsEmails = new EmailDAO().readListOfObjectsPaginationALL(offset, noOfRecords, queryMain);

					String emails = null;
					StringBuilder sbN = new StringBuilder();

					if (!parentsEmails.isEmpty()) {
						for (Parents parents : parentsEmails) {
							sbN.append(parents.getEmail());
							sbN.append(",");
						}
						emails = sbN.toString();
						emails = emails.substring(0, emails.length() - 1);
						log.error("emails are *** " + emails);
						result.setSuccess(sendEmail(emails, DataUtil.emptyString(dto.getSubject()), DataUtil.emptyString(dto.getMessageBody())));
					}

					offset = offset + 100;
				}
			}
			result.setSuccess(false);
		}catch (Exception e){
			e.printStackTrace();
			result.setSuccess(true);
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
