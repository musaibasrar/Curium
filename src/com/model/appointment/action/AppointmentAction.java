/**
 * 
 */
package com.model.appointment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.appointment.service.AppointmentService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class AppointmentAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public AppointmentAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {
		
		if (action.equalsIgnoreCase("addAppointment")) {
			url = addAppointment();
		}else if (action.equalsIgnoreCase("viewAllAppointments")) {
			url = viewAllAppointments();
		}else if (action.equalsIgnoreCase("completeAppointments")) {
			url = completeAppointments();
		}else if (action.equalsIgnoreCase("cancelAppointments")) {
			url = cancelAppointments();
		}else if (action.equalsIgnoreCase("appointmentReport")) {
			url = appointmentReport();
		}else if (action.equalsIgnoreCase("generateAppointmentsReport")) {
			url = generateAppointmentsReport();
		}else if (action.equalsIgnoreCase("printAppointmentsReport")) {
			url = printAppointmentsReport();
		}
		return url;
	}
	
	
	private String printAppointmentsReport() {
		return "printappointmentsreport.jsp";
	}

	private String generateAppointmentsReport() {
		new AppointmentService(request, response).generateAppointmentsReport();
		return appointmentReport();
	}

	private String appointmentReport() {
		new StudentService(request, response).viewAllStudentsList();
		return "appointmentsreport.jsp";
	}

	private String cancelAppointments() {
		new AppointmentService(request, response).cancelAppointments();
		return viewAllAppointments();
	}

	private String completeAppointments() {
		new AppointmentService(request, response).completeAppointments();
		return viewAllAppointments();
	}

	private String viewAllAppointments() {
		
		if(new AppointmentService(request, response).viewAllAppointments()){
			return "appointments.jsp";
		}else{
			return "error.jsp";
		}
	}

	private String addAppointment() {
		
		if(new AppointmentService(request, response).addAppointment()){
			return "appointmentsuccess.jsp";
		}else{
			return "error.jsp";
		}
	}
}
