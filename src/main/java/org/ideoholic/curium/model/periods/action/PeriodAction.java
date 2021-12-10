/**
 * 
 */
package org.ideoholic.curium.model.periods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.documents.service.DocumentService;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.feescategory.service.FeesService;
import org.ideoholic.curium.model.periods.service.PeriodService;
import org.ideoholic.curium.model.stampfees.service.StampFeesService;
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
@RequestMapping("/PeriodProcess")
public class PeriodAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession httpSession;
	String url;
	private String error ="error";

	

	@GetMapping("/viewTeacherTimeTable")
	public String viewTeacherTimeTable() {
		
		if(new PeriodService(request, response).viewTeacherTimeTable()){
			return "teachertimetableview";
		}
		return error;
		
	}

	@GetMapping("/generateTeacherTimeTable")
	public String generateTeacherTimeTable() {
		
		if(new EmployeeService(request, response).ViewAllEmployee()){
			return "teachertimetable";
		}
		return error;
	}

	@GetMapping("/generateTimeTable")
	public String generateTimeTable() {
		
		if(new PeriodService(request, response).generateTimeTable()){
			return "classestimetable";
		}
		return error;
	}

	@PostMapping("/deletePeriods")
	public String deletePeriods() {
		if(new PeriodService(request, response).deletePeriods()){
			return periodConfiguration();
		}
		
		return error;
	}

	@GetMapping("/viewTimeTable")
	public String viewTimeTable() {
		
		if(new PeriodService(request, response).viewTimeTable()){
			return "timetable";
		}
		
		return error;
	}

	@PostMapping("/savePeriods")
	public String savePeriods() {
		
		if(new PeriodService(request, response).savePeriods()){
			return periodConfiguration();
		}
		return error;
	}

	@GetMapping("/periodConfiguration")
	public String periodConfiguration() {
		
		if(new PeriodService(request, response).periodConfiguration()){
			return "periodmaster";
		}
		return error;
	}


	@GetMapping("/transferCertificate")
	public String transferCertificate() {
		if(new DocumentService(request, response).transferCertificate()){
			return "transfercertificate";
		}
        return error;
	}
}
