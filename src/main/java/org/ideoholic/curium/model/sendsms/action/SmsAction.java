/**
 * 
 */
package org.ideoholic.curium.model.sendsms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.service.StudentService;
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
@RequestMapping("/SMSProcess")
public class SmsAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@PostMapping("/sendSMS")
	public String sendSMS() {
		new StandardService(request, response).viewClasses();
		new EmployeeService(request, response).viewDepartments();
		return "sendsms";
	}

	@PostMapping("/sendStaffSMS")
	public String sendStaffSMS() {
		if (new SmsService(request, response).sendStaffSMS()) {
			return "successsms";
		}
		return "errorsms";
	}

	@PostMapping("/sendAllSMS")
	public String sendAllSMS() {
		if (new SmsService(request, response).sendAllSMS()) {
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
