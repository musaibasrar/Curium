/**
 * 
 */
package com.model.subjectdetails.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.examdetails.dao.ExamDetailsDAO;
import com.model.examdetails.dto.Exams;
import com.model.subjectdetails.dao.SubjectDetailsDAO;
import com.model.subjectdetails.dto.Subject;
import com.util.DataUtil;

/**
 * @author Musaib_2
 *
 */
public class SubjectDetailsService {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	
	public SubjectDetailsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public boolean readListOfSubjects() {
		boolean result = false;
	    try {
	    	List<Subject> list = new SubjectDetailsDAO().readListOfSubjects(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
	        httpSession.setAttribute("listSubject", list);

	        result = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = false;
	    }
		return result;
	}

	public boolean addSubject() {
		Subject subject = new Subject();
		boolean result = true;
		
		if(httpSession.getAttribute("branchid")!=null){
			subject.setSubjectname(DataUtil.emptyString(request.getParameter("subjectname")));
			subject.setMinmarks(DataUtil.parseInt(request.getParameter("minmarks")));
			subject.setMaxmarks(DataUtil.parseInt(request.getParameter("maxmarks")));
			subject.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
			
			//Add examlevels
			String[] examlevelids = request.getParameterValues("examlevel");
			subject = new SubjectDetailsDAO().addSubject(subject,examlevelids);
			 
			if(subject == null){
				result=false;
			}
		}
		
		return result;
	}

	public boolean deleteMultiple() {
		String[] examIds = request.getParameterValues("subjectIDs");
		boolean result = false;
		 if(examIds!=null){
	        List ids = new ArrayList();
	        for (String id : examIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + examIds.length);
	        new SubjectDetailsDAO().deleteMultiple(ids);
	        result = true;
	}else{
		result = false;
	}
		 return result;
	}

    public Subject getSubjectDetails(String subject) {
       return new SubjectDetailsDAO().getSubjectDetails(subject);        
    }
	
	
	
}
