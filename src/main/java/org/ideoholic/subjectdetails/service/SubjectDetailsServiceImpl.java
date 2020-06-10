package org.ideoholic.subjectdetails.service;

import java.util.ArrayList;
import java.util.List;

import com.model.subjectdetails.dao.SubjectDetailsDAO;
import com.model.subjectdetails.dto.Subject;
import com.model.subjectdetails.dto.Subjectmaster;
import com.util.DataUtil;

public class SubjectDetailsServiceImpl implements SubjectDetailsService {
	
	public String readListOfSubjects(String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		boolean result = false;
	    try {
	    	List<Subject> list = new SubjectDetailsDAO().readAllSubjects(Integer.parseInt(branchId.toString()));
	    	sb.append("listSubject").append(list);

	        result = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        result = false;
	    }
	    sb.append("}");
		return sb.toString();
	}
	
	public String addSubject(String branchId,String subjectName,String minMarks,String maxMarks, String examName,String examclass) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Subject subject = new Subject();
		boolean result = true;
		
		if(branchId!=null){
			subject.setBranchid(Integer.parseInt(branchId.toString()));
			subject = new SubjectDetailsDAO().addSubject(subject);
			 
			if(subject == null){
				result=false;
			}
		}
		
		sb.append("}");
		return sb.toString();
	}
	
	public String deleteMultiple(String[] examIds) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
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
		 sb.append("}");
		 return sb.toString();
	}
	
	public String addSubjectMaster(String branchId, String subjectName) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		Subjectmaster subject = new Subjectmaster();
		boolean result = true;
		
		if(branchId!=null){
			subject.setBranchid(Integer.parseInt(branchId.toString()));
			subject = new SubjectDetailsDAO().addSubjectMaster(subject);
			 
			if(subject == null){
				result=false;
			}
		}
		sb.append("}");
		return sb.toString();
	}
	
	public String readListOfSubjectNames(String branchId) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
	    try {
	    	List<Subject> list = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(branchId.toString()));
	    	sb.append("listSubjectNames").append(list);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    sb.append("}");
		return sb.toString();
	}

	public String deleteMultipleSubjects(String[] examIds) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		boolean result = false;
		 if(examIds!=null){
	        List ids = new ArrayList();
	        for (String id : examIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + examIds.length);
	        new SubjectDetailsDAO().deleteMultipleSubjects(ids);
	        result = true;
	}else{
		result = false;
	}
		 sb.append("}");
		 return sb.toString();
	}
}
