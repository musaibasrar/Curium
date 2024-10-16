/**
 * 
 */
package org.ideoholic.curium.model.sendsms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.academicyear.action.YearActionAdapter;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.sendsms.service.SmsService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
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
	@Autowired
	StandardActionAdapter standardActionAdapter;
	@Autowired
	YearActionAdapter yearActionAdapter;
	@Autowired
	private EmployeeActionAdapter employeeActionAdapter;

	@GetMapping("/sendSMS")
	public String sendSMS() {
		standardActionAdapter.viewClasses();
		employeeActionAdapter.viewDepartments();
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
		yearActionAdapter.updateYear();
		return "academicyear";

	}
	
	@PostMapping("/sendSMSFeesDueReminder")
		public String sendSMSFeesDueReminder() {
			if (new SmsService(request, response).sendSMSFeesDueReminder()) {
				return "successsms";
			}
			return "errorsms";
	}
	
	@GetMapping("/SMSDeliveryReport")
	public String SMSDeliveryReport() {
		if (new SmsService(request, response).SMSDeliveryReport()) {
			return "smsdeliveryreport";
		}
		return "error";
	}

}
