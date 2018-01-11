/**
 * 
 */
package com.model.examdetails.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.adminexpenses.dao.AdminDetailsDAO;
import com.model.examdetails.dao.ExamDetailsDAO;
import com.model.examdetails.dto.Exams;
import com.util.DataUtil;

/**
 * @author Musaib_2
 *
 */
public class ExamDetailsService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	
	public ExamDetailsService(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}


	public Boolean addExam() {
		// TODO Auto-generated method stub
		Exams exams = new Exams();
		boolean result = true;
		exams.setExamname(DataUtil.emptyString(request.getParameter("examname")));
		exams = new ExamDetailsDAO().addExams(exams);
		 
		if(exams == null){
			result=false;
		}
		return result;
	}


	public boolean readListOfExams() {
		
		boolean result = true;
		
		List<Exams> exams = new ExamDetailsDAO().readListOfExams();
		 httpSession.setAttribute("examdetails", exams);
		if(exams == null){
			result=false;
		}
		return result;
	}


	public boolean deleteMultiple() {
		String[] examIds = request.getParameterValues("examIDs");
		boolean result = false;
		 if(examIds!=null){
	        List ids = new ArrayList();
	        for (String id : examIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + examIds.length);
	        new ExamDetailsDAO().deleteMultiple(ids);
	        result = true;
	}else{
		result = false;
	}
		 return result;
	}
}
