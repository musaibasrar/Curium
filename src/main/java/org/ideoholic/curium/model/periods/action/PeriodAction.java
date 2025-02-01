/**
 * 
 */
package org.ideoholic.curium.model.periods.action;

import org.ideoholic.curium.model.documents.action.DocumentActionAdapter;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.periods.service.PeriodService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.subjectdetails.action.SubjectDetailsActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Ideoholic
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
	@Autowired
	private DocumentActionAdapter documentActionAdapter;
	@Autowired
	StandardActionAdapter standardActionAdapter;
	@Autowired
	EmployeeActionAdapter employeeActionAdapter;
	@Autowired
	private SubjectDetailsActionAdapter subjectDetailsActionAdapter;
	@Autowired
	private PeriodActionAdapter periodActionAdapter;
	String url;
	private String error ="error";

	

	@GetMapping("/viewTeacherTimeTable")
	public String viewTeacherTimeTable() {
		
		if(periodActionAdapter.viewTeacherTimeTable()){
			return "teachertimetableview";
		}
		return error;
		
	}

	@GetMapping("/generateTeacherTimeTable")
	public String generateTeacherTimeTable() {
		
		if(employeeActionAdapter.ViewAllEmployee()){
			return "teachertimetable";
		}
		return error;
	}

	
	@RequestMapping(value = "/generateTimeTable", method = { RequestMethod.GET, RequestMethod.POST })
	public String generateTimeTable() {
		
		if(periodActionAdapter.generateTimeTable()){
			return "classestimetable";
		}
		return error;
	}

	@PostMapping("/deletePeriods")
	public String deletePeriods() {
		if(periodActionAdapter.deletePeriods()){
			return periodConfiguration();
		}
		
		return error;
	}

	@GetMapping("/viewTimeTable")
	public String viewTimeTable() {
		
		if(periodActionAdapter.viewTimeTable()){
			return "timetable";
		}
		
		return error;
	}

	@PostMapping("/savePeriods")
	public String savePeriods() {
		
		if(periodActionAdapter.savePeriods()){
			return periodConfiguration();
		}
		return error;
	}

	@GetMapping("/periodConfiguration")
	public String periodConfiguration() {
		
		if(periodActionAdapter.periodConfiguration()){
			return "periodmaster";
		}
		return error;
	}


	@GetMapping("/transferCertificate")
	public String transferCertificate() {
		if(documentActionAdapter.transferCertificate()){
			return "transfercertificate";
		}
        return error;
	}
	
	@GetMapping("/updatePeriodDetails")
	public String updatePeriodDetails() {
		periodActionAdapter.updatePeriodDetails();
		periodActionAdapter.getPeriodDetail();
		standardActionAdapter.viewClasses(); 
		return "updatetimetable";
	}
	
	@PostMapping("/updatenewPeriodDetails")
	public String updatenewPeriodDetails() {
		if(periodActionAdapter.updatenewPeriodDetails()) {
		return "timetableupdatesaved";
	}
		return error;
	}
	

}
