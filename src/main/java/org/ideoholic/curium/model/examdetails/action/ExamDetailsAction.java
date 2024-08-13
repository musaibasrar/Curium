/**
 * 
 */
package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.model.academicyear.action.YearActionAdapter;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.service.StudentService;
import org.ideoholic.curium.model.subjectdetails.action.SubjectDetailsActionAdapter;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Musaib_2
 *
 */
@Controller
@RequestMapping("/ExamDetailsProcess")
public class ExamDetailsAction {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private YearActionAdapter yearActionAdapter;

	@Autowired
	private StandardActionAdapter standardActionAdapter;

	@Autowired
	private ExamDetailsActionAdapter examDetailsActionAdapter;
	@Autowired
	private SubjectDetailsActionAdapter subjectDetailsActionAdapter;
	
	private String error = "error";
	

	@PostMapping("/printPreviewHallTicket")
	public String printPreviewHallTicket() {
		
		examDetailsActionAdapter.printPreviewHallTicket();
		return "printpreviewhallticket";
	}
	
	@PostMapping("/searchHallTicketDetails")
	public String searchHallTicketDetails() {
		
		examDetailsActionAdapter.getExamScheduleDetails();
		examDetailsActionAdapter.readListOfExams();
		subjectDetailsActionAdapter.readListOfSubjects();
		
		return "generatehallticket";
	}

	@GetMapping("/generateHallTicket")
	public String generateHallTicket() {
		
		boolean result;
		
		result = examDetailsActionAdapter.readListOfExams();
		if (!result) 
			return error;
		result = standardActionAdapter.viewClasses();
		if (!result) 
			return error;
		result = subjectDetailsActionAdapter.readListOfSubjects();
		if (!result) 
			return error;
		result = yearActionAdapter.getYear();
		if (!result) 
			return error;
		result = new StudentService(request, response, standardActionAdapter).viewAllStudentsList();
		if (!result) 
			return error;
		
		return "generatehallticket";
	}

	@PostMapping("/deleteExamSchedule")
	public String deleteExamSchedule() {
		
		if(examDetailsActionAdapter.deleteExamSchedule()){
			return examSchedule();
		}else{
			return "error";
		}
	}

	@PostMapping("addSchedule")
	public String addSchedule() {
		
		if(examDetailsActionAdapter.addSchedule()){
			return examSchedule();
		}else{
			return "error";
		}
	}
	
	
	@GetMapping("/examSchedule")
	public String examSchedule() {
		
		boolean result;
		
		result = examDetailsActionAdapter.readListOfExams();
		if (!result) 
			return error;
		result = standardActionAdapter.viewClasses();
		if (!result) 
			return error;
		subjectDetailsActionAdapter.readListOfSubjectNames();
		if (!result)
			return error;
		result = yearActionAdapter.getYear();
		if (!result) 
			return error;
		result = examDetailsActionAdapter.getExamSchedule();
		if (!result) 
			return error;
		
		return "examschedule";
		
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		if(examDetailsActionAdapter.deleteMultiple()){
			return readListOfExams();
		}else{
			return "error";
		}
	}

	@GetMapping("/readListOfExams")
	public String readListOfExams() {
		if(examDetailsActionAdapter.readListOfExams()){
			return "ExamDetails";
		}else{
			return "error";
		}
	}

	@PostMapping("/addExam")
	public String addExam() {
		
		if(examDetailsActionAdapter.addExam()){
			return readListOfExams();
		}else{
			return "error";
		}
        
	}

}