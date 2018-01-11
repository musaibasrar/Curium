/**
 * 
 */
package com.model.examdetails.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.examdetails.service.ExamDetailsService;
import com.model.stampfees.service.StampFeesService;

/**
 * @author Musaib_2
 *
 */
public class ExamDetailsAction {

	HttpServletRequest request;
	HttpServletResponse response;
	String url;
	
	public ExamDetailsAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String execute(String action) {
		if (action.equalsIgnoreCase("addExam")) {
			System.out.println("Action is addExam");
			url = addExam();
		}else if (action.equalsIgnoreCase("readListOfExams")) {
			System.out.println("Action is readListOfExams");
			url = readListOfExams();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			System.out.println("Action is deleteMultiple");
			url = deleteMultiple();
		}
		return url;
	}

		
	private String deleteMultiple() {
		if(new ExamDetailsService(request, response).deleteMultiple()){
			return "Controller?process=ExamDetailsProcess&action=readListOfExams";
		}else{
			return "error.jsp";
		}
	}

	private String readListOfExams() {
		if(new ExamDetailsService(request, response).readListOfExams()){
			return "ExamDetails.jsp";
		}else{
			return "error.jsp";
		}
	}

	private String addExam() {
		
		if(new ExamDetailsService(request, response).addExam()){
			return "Controller?process=ExamDetailsProcess&action=readListOfExams";
		}else{
			return "error.jsp";
		}
        
	}

}
