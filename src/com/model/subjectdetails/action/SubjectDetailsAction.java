package com.model.subjectdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.examdetails.service.ExamDetailsService;
import com.model.examlevels.dto.Examleveldetails;
import com.model.examlevels.service.ExamLevelService;
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
			url = readListOfSubjects();
		}else if (action.equalsIgnoreCase("addSubject")) {
			url = addSubject();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			url = deleteMultiple();
		}
		
		return url;
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

	private String readListOfSubjects() {
		new SubjectDetailsService(request, response).readListOfSubjects();
		new ExamLevelService(request, response).examLevels();
        return "SubjectDetails.jsp";
	}
	
}