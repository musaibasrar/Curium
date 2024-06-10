/**
 * 
 */
package org.ideoholic.curium.model.appointment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.appointment.service.AppointmentService;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/AppointmentProcess")
public class AppointmentAction {


	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private AppointmentActionAdapter appointmentActionAdapter;

	@PostMapping("/download")
	private String download() {
		if(new AppointmentService(request, response).download()) {
			return "exportsuccessappointment";
		}
		return "exportfailure";
	}

	@PostMapping("/exportAppointmentsReport")
	private String exportAppointmentsReport() {
		appointmentActionAdapter.exportAppointmentsReport();
		return "exportsuccessappointment";
	}

	@PostMapping("/printAppointmentsReport")
	private String printAppointmentsReport() {
		return "printappointmentsreport";
	}

	@PostMapping("/generateAppointmentsReport")
	private String generateAppointmentsReport() {
		appointmentActionAdapter.generateAppointmentsReport();
		return generateAppointmentsReport();
	}

	@GetMapping("/appointmentReport")
	private String appointmentReport() {
		new StudentService(request, response).viewAllStudentsList();
		return "appointmentsreport";
	}

	@PostMapping("/cancelAppointments")
	private String cancelAppointments() {
		appointmentActionAdapter.cancelAppointments();
		return viewAllAppointments();
	}

	@PostMapping("/completeAppointments")
	private String completeAppointments() {
		appointmentActionAdapter.completeAppointments();
		return viewAllAppointments();
	}

	@RequestMapping(value = "/viewAllAppointments", method = { RequestMethod.GET, RequestMethod.POST })
	private String viewAllAppointments() {
		if(appointmentActionAdapter.viewAllAppointments()){
			return "appointments";
		}else{
			return "error";
		}
	}

	@PostMapping("/addAppointment")
	private String addAppointment() {
		if(appointmentActionAdapter.addAppointment()){
			new StudentService(request, response).viewAllStudentsParents();
			new EmployeeService(request, response).ViewAllEmployee();
			return "viewAllWithParents";
		}else{
			return "error";
		}
	}
	
	@PostMapping("/updateAppointment")
	private String updateAppointment() {

		if(appointmentActionAdapter.updateAppointment()){
			return viewAllAppointments();
			//return "appointmentsuccess";
		}else{
			return "error";
		}
	}
}
