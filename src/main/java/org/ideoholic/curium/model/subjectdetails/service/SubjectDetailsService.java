/**
 * 
 */
package org.ideoholic.curium.model.subjectdetails.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.examdetails.dto.ExamIdsDto;
import org.ideoholic.curium.model.subjectdetails.dao.SubjectDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dto.*;
import org.ideoholic.curium.util.DataUtil;

/**
 * @author Musaib_2
 *
 */
public class SubjectDetailsService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession httpSession;
	
	public SubjectDetailsService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public SubjectsResponseDto readListOfSubjects(String branchId) {
		SubjectsResponseDto result = new SubjectsResponseDto();

	    try {
	    	List<Subject> list = new SubjectDetailsDAO().readAllSubjects(Integer.parseInt(branchId));
	        result.setList(list);

			result.setSuccess(true);
	    } catch (Exception e) {
	        e.printStackTrace();
			result.setSuccess(false);
	    }
		return result;
	}

	public ResultResponse addSubject(SubjectDto subjectDto, String branchId, String userLoginId) {
		Subject subject = new Subject();
		boolean result;
		
		if(branchId!=null){
			String[] subjectNameId = DataUtil.emptyString(subjectDto.getSubjectname()).split(":");
			subject.setSubjectname(subjectNameId[0]);
			subject.setSubjectid(Integer.parseInt(subjectNameId[1]));	
			subject.setMinmarks(DataUtil.parseInt(subjectDto.getMinMarks()));
			subject.setMaxmarks(DataUtil.parseInt(subjectDto.getMaxMarks()));
			subject.setExamname(DataUtil.emptyString(subjectDto.getExamName()));
			subject.setExamclass(DataUtil.emptyString(subjectDto.getExamClass()));
			subject.setBranchid(Integer.parseInt(branchId));
			subject.setUserid(Integer.parseInt(userLoginId));
			subject = new SubjectDetailsDAO().addSubject(subject);
			 
			if(subject == null){
				result=false;
				return ResultResponse.builder().success(result).build();
			}
		}
		
		return ResultResponse.builder().build();
	}

	public ResultResponse deleteMultiple(ExamIdsDto examIdsDto) {
		String[] examIds = examIdsDto.getExamIds();
		boolean result;
		 if(examIds!=null){
	        List<Integer> ids = new ArrayList();
	        for (String id : examIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + examIds.length);
	        new SubjectDetailsDAO().deleteMultiple(ids);
	        result = true;
			return  ResultResponse.builder().success(result).build();
	}else {
			 result = false;
			 return ResultResponse.builder().success(result).build();
		 }
	}

	public boolean addSubjectMaster() {
		Subjectmaster subject = new Subjectmaster();
		boolean result = true;
		
		if(httpSession.getAttribute("branchid")!=null){
			subject.setSubjectname(DataUtil.emptyString(request.getParameter("subjectname")));
			subject.setBranchid(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
			subject.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
			subject = new SubjectDetailsDAO().addSubjectMaster(subject);
			 
			if(subject == null){
				result=false;
			}
		}
		return result;
	}

	public void readListOfSubjectNames() {
	    try {
	    	List<Subject> list = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
	        httpSession.setAttribute("listSubjectNames", list);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public boolean deleteMultipleSubjects() {
		String[] examIds = request.getParameterValues("subjectIDs");
		boolean result = false;
		 if(examIds!=null){
	        List<Integer> ids = new ArrayList();
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
		 return result;
	}
	
	
	
}
