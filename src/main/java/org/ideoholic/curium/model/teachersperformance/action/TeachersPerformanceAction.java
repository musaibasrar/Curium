package org.ideoholic.curium.model.teachersperformance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.subjectdetails.service.SubjectDetailsService;
import org.ideoholic.curium.model.teachersperformance.service.TeacherPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/TeachersPerformanceProcess")
public class TeachersPerformanceAction {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;

	@GetMapping("/SearchTeachers")
	public String SearchTeachers() {
		new StandardService(request, response).viewClasses();
		new SubjectDetailsService(request, response).readListOfSubjectNames();
		new ExamDetailsService(request, response).readListOfExams();
		return "teachersPerormance";
	}
	
	@PostMapping("/searchForTeacherDetail")
	public String searchForTeacherDetail() {
		new TeacherPerformanceService(request, response).getDetailofteacher();
		return "performanceGraph";
	}


}
