package org.ideoholic.curium.model.subjectdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.examdetails.action.ExamDetailsActionAdapter;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
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
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;

	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private ExamDetailsActionAdapter examDetailsActionAdapter;
    @Autowired
	private SubjectDetailsActionAdapter subjectDetailsActionAdapter;
	
	
	@PostMapping("/deleteMultipleSubjects")
	public String deleteMultipleSubjects() {
		if(new SubjectDetailsService(request, response).deleteMultipleSubjects()){
			return readListOfSubjectNames();
		}else{
			return "error";
		}
	}

	@GetMapping("/readListOfSubjectNames")
	public String readListOfSubjectNames() {
		new SubjectDetailsService(request, response).readListOfSubjectNames();
        return "SubjectMaster";
	}

	@PostMapping("/addSubjectMaster")
	public String addSubjectMaster() {
		if(new SubjectDetailsService(request, response).addSubjectMaster()){
			return readListOfSubjectNames();
		}else{
			return "error";
		}
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		if(new SubjectDetailsService(request, response).deleteMultiple()){
			return readListOfSubjectsExams();
		}else{
			return "error";
		}
	}

	@PostMapping("/addSubject")
	public String addSubject() {
		if(subjectDetailsActionAdapter.addSubject()){
			return readListOfSubjectsExams();
		}else{
			return "error";
		}
	}

	@GetMapping("/readListOfSubjects")
	public String readListOfSubjectsExams() {
		subjectDetailsActionAdapter.readListOfSubjects();
		new SubjectDetailsService(request, response).readListOfSubjectNames();
		examDetailsActionAdapter.readListOfExams();
		standardActionAdapter.viewClasses();
        return "SubjectDetails";
	}
	
}
