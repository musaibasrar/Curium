/**
 * 
 */
package org.ideoholic.curium.model.sendemail.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.sendemail.service.EmailService;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Musaib_2
 * 
 */

@Controller
@RequestMapping("/EmailProcess")
public class EmailAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@PostMapping("/sendStaffSMS")
	public String sendStaffSMS() {
		if (new SmsService(request, response).sendStaffSMS()) {
			return "successsms";
		}
		return "errorsms";
	}

	
	@PostMapping("/sendAllEmail")
	public String sendAllEmail() {
		if (new EmailService(request, response).sendAllEmail()) {
			return "successsms";
		}
		return "errorsms";
	}

	@PostMapping("/sendNumbersSMS")
	public String sendNumbersSMS() {

		if (new SmsService(request, response).sendNumbersSMS()) {
			return "successsms";
		}
		return "errorsms";
	}

	@GetMapping("/updateYear")
	public String updateYear() {
		new YearService(request, response).updateYear();
		return "academicyear";

	}

}
