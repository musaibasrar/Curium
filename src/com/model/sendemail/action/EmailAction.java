/**
 * 
 */
package com.model.sendemail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.service.YearService;
import com.model.sendemail.service.EmailService;
import com.model.sendsms.service.SmsService;

/**
 * @author Musaib_2
 * 
 */
public class EmailAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public EmailAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("sendAllEmail")) {
			url = sendAllEmail();
		} else if (action.equalsIgnoreCase("sendNumbersSMS")) {
			url = sendNumbersSMS();
		}else if (action.equalsIgnoreCase("sendStaffSMS")) {
			url = sendStaffSMS();
		}
		
		return url;
	}

	

	

	private String sendStaffSMS() {
		if(new SmsService(request, response).sendStaffSMS()){
			return "successsms.jsp";
		}
		return "errorsms.jsp";
	}

	private String sendAllEmail() {
		if(new EmailService(request, response).sendAllEmail()){
			return "successsms.jsp";
		}
		return "errorsms.jsp";
	}

	private String sendNumbersSMS() { 
		
		if(new SmsService(request, response).sendNumbersSMS()){
			return "successsms.jsp";
		}
		return "errorsms.jsp";		
    }

	private String updateYear() {
		 new YearService(request, response).updateYear();
	            return "academicyear.jsp";
	       
		
	}

	

}
