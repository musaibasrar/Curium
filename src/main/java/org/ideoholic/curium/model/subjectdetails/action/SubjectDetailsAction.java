package org.ideoholic.curium.model.subjectdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/SubjectDetailsProcess")
public class SubjectDetailsAction {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	
	@PostMapping("/deleteMultipleSubjects")
	private String deleteMultipleSubjects() {
		if(new SubjectDetailsService(request, response).deleteMultipleSubjects()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjectNames";
		}else{
			return "error.jsp";
		}
	}

	@GetMapping("/readListOfSubjectNames")
	private String readListOfSubjectNames() {
		new SubjectDetailsService(request, response).readListOfSubjectNames();
        return "SubjectMaster.jsp";
	}

	@PostMapping("/addSubjectMaster")
	private String addSubjectMaster() {
		if(new SubjectDetailsService(request, response).addSubjectMaster()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjectNames";
		}else{
			return "error.jsp";
		}
	}

	@PostMapping("/deleteMultiple")
	private String deleteMultiple() {
		if(new SubjectDetailsService(request, response).deleteMultiple()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjects";
		}else{
			return "error.jsp";
		}
	}

	@PostMapping("/addSubject")
	private String addSubject() {
		if(new SubjectDetailsService(request, response).addSubject()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjects";
		}else{
			return "error.jsp";
		}
	}

	@GetMapping("/readListOfSubjects")
	private String readListOfSubjectsExams() {
		new SubjectDetailsService(request, response).readListOfSubjects();
		new SubjectDetailsService(request, response).readListOfSubjectNames();
		new ExamDetailsService(request, response).readListOfExams();
		new StandardService(request, response).viewClasses();
        System.out.println("IN action's view all Subjects");
        return "SubjectDetails.jsp";
	}
	
}
