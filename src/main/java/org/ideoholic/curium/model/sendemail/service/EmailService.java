package org.ideoholic.curium.model.sendemail.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.sendemail.dao.EmailDAO;
import org.ideoholic.curium.model.sendemail.dao.EmailDAO.QUERY_TYPE;
import org.ideoholic.curium.model.sendemail.dto.SendAllEmailDto;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

@Slf4j
@Service
public class EmailService {
	private final String SUCCESS = "Success";
	
	@Autowired
	private EmailDAO emailDao;
	    
	private static DecimalFormat df2 = new DecimalFormat(".##");


	public ResultResponse sendAllEmail(SendAllEmailDto dto, String branchId) {
		ResultResponse result = ResultResponse.builder().build();
		QUERY_TYPE queryType = QUERY_TYPE.NONE;
		int noOfRecords = 100;
		int offset=0;

		try {
			if (branchId != null) {

				String addClass = dto.getAddClass();
				String addSec = dto.getAddSec();
				String conClassStudying = "";

				if (addClass.contains("ALL")) {
					queryType = QUERY_TYPE.ALL_PARENTS;
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
						queryType = QUERY_TYPE.ALL_PARENTS_WITH_CLASS;
					}
				}

				double totalEmails = emailDao.countEmails(queryType, conClassStudying, branchId);
				int iterations = (int) Math.ceil(totalEmails / 100);

				log.error("query type:{}", queryType);

				for (int i = 0; i < iterations; i++) {
					List<Parents> parentsEmails = emailDao.readListOfObjectsPaginationALL(queryType, conClassStudying, branchId, offset, noOfRecords);

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
						String emailResult = sendEmail(emails, DataUtil.emptyString(dto.getSubject()), DataUtil.emptyString(dto.getMessageBody()));
						if(emailResult.equals(SUCCESS))	result.setSuccess(true);
						result.setMessage(emailResult);
					}

					offset = offset + 100;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			result.setMessage(e.getMessage());
			result.setSuccess(false);
		}
        return result;
		
	}

	
	private String sendEmail(String emails, String subject, String message) {
		String result = SUCCESS;
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
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result = e.getMessage();
		}
		
		return result;
	}
	
}
