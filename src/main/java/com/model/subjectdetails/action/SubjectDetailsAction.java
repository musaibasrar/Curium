package com.model.subjectdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.examdetails.service.ExamDetailsService;
import com.model.std.service.StandardService;
import com.model.student.service.StudentService;
import com.model.subjectdetails.service.SubjectDetailsService;


public class SubjectDetailsAction {

	HttpServletRequest request;
	HttpServletResponse response;
	String url;
	
	public SubjectDetailsAction(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String execute(String action) {
		
		if (action.equalsIgnoreCase("readListOfSubjects")) {
			url = readListOfSubjectsExams();
		}else if (action.equalsIgnoreCase("addSubject")) {
			url = addSubject();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			url = deleteMultiple();
		}else if (action.equalsIgnoreCase("addSubjectMaster")) {
			url = addSubjectMaster();
		}else if (action.equalsIgnoreCase("readListOfSubjectNames")) {
			url = readListOfSubjectNames();
		}else if (action.equalsIgnoreCase("deleteMultipleSubjects")) {
			url = deleteMultipleSubjects();
		}
		
		return url;
	}

	private String deleteMultipleSubjects() {
		if(new SubjectDetailsService(request, response).deleteMultipleSubjects()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjectNames";
		}else{
			return "error.jsp";
		}
	}

	private String readListOfSubjectNames() {
		new SubjectDetailsService(request, response).readListOfSubjectNames();
        return "SubjectMaster.jsp";
	}

	private String addSubjectMaster() {
		if(new SubjectDetailsService(request, response).addSubjectMaster()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjectNames";
		}else{
			return "error.jsp";
		}
	}

	private String deleteMultiple() {
		if(new SubjectDetailsService(request, response).deleteMultiple()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjects";
		}else{
			return "error.jsp";
		}
	}

	private String addSubject() {
		if(new SubjectDetailsService(request, response).addSubject()){
			return "Controller?process=SubjectDetailsProcess&action=readListOfSubjects";
		}else{
			return "error.jsp";
		}
	}

	private String readListOfSubjectsExams() {
		new SubjectDetailsService(request, response).readListOfSubjects();
		new SubjectDetailsService(request, response).readListOfSubjectNames();
		new ExamDetailsService(request, response).readListOfExams();
		new StandardService(request, response).viewClasses();
        System.out.println("IN action's view all Subjects");
        return "SubjectDetails.jsp";
	}
	
}
