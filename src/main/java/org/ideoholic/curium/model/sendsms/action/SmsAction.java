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

/**
 * @author Musaib_2
 * 
 */
public class SmsAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public SmsAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("sendAllSMS")) {
			url = sendAllSMS();
		} else if (action.equalsIgnoreCase("sendNumbersSMS")) {
			url = sendNumbersSMS();
		}else if (action.equalsIgnoreCase("sendStaffSMS")) {
			url = sendStaffSMS();
		}else if (action.equalsIgnoreCase("sendSMS")) {
			url = sendSMS();
		}
		
		return url;
	}

	

	

	private String sendSMS() {
		new StandardService(request, response).viewClasses();
		new EmployeeService(request, response).viewDepartments();
		return "sendsms.jsp";
	}

	private String sendStaffSMS() {
		if(new SmsService(request, response).sendStaffSMS()){
			return "successsms.jsp";
		}
		return "errorsms.jsp";
	}

	private String sendAllSMS() {
		if(new SmsService(request, response).sendAllSMS()){
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
