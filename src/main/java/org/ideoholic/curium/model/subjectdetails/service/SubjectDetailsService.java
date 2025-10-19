/**
 * 
 */
package org.ideoholic.curium.model.subjectdetails.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.subjectdetails.dao.SubjectDetailsDAO;
import org.ideoholic.curium.model.subjectdetails.dto.SubSubject;
import org.ideoholic.curium.model.subjectdetails.dto.SubSubjectDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubSubjectsResponseDto;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectDto;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectIdsDto;
import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;
import org.ideoholic.curium.model.subjectdetails.dto.SubjectsResponseDto;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.stereotype.Service;

/**
 * @author Musaib_2
 *
 */
@Service
public class SubjectDetailsService {

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
		boolean result= true;
		
		if(branchId!=null){
			String[] subjectNameId = DataUtil.emptyString(subjectDto.getSubjectName()).split(":");
			subject.setSubjectname(subjectNameId[0]);
			subject.setSubjectid(Integer.parseInt(subjectNameId[1]));	
			subject.setMinmarks(Float.parseFloat(subjectDto.getMinMarks()));
			subject.setMaxmarks(Float.parseFloat(subjectDto.getMaxMarks()));
			subject.setExamname(DataUtil.emptyString(subjectDto.getExamName()));
			subject.setExamclass(DataUtil.emptyString(subjectDto.getExamClass()));
			subject.setBranchid(Integer.parseInt(branchId));
			subject.setUserid(Integer.parseInt(userLoginId));
			subject = new SubjectDetailsDAO().addSubject(subject);
			 
			if(subject!=null){
				return ResultResponse.builder().success(result).build();
			}
		}
		
		return ResultResponse.builder().build();
	}

	public ResultResponse deleteMultiple(SubjectIdsDto subjectIdsDto) {
		String[] examIds = subjectIdsDto.getSubjectIds();
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

	public ResultResponse addSubjectMaster(SubjectDto subjectDto, String branchId, String userLoginId) {
		Subjectmaster subject = new Subjectmaster();
		boolean result;
		
		if(branchId!=null){
			subject.setSubjectname(DataUtil.emptyString(subjectDto.getSubjectName()));
			subject.setBranchid(Integer.parseInt(branchId));
			subject.setUserid(Integer.parseInt(userLoginId));
			subject = new SubjectDetailsDAO().addSubjectMaster(subject);
			 
			if(subject == null){
				result=false;
				return ResultResponse.builder().success(result).build();
			}
			result= true;
			return ResultResponse.builder().success(result).build();
		}
		return ResultResponse.builder().build();
	}

	public SubjectsResponseDto readListOfSubjectNames(String branchId) {
		SubjectsResponseDto result = new SubjectsResponseDto();
	    try {
	    	List<Subjectmaster> list = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(branchId));
			result.setListSubjectNames(list);
			result.setSuccess(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}

	public ResultResponse deleteMultipleSubjects(SubjectIdsDto subjectIdsDto) {
		String[] examIds = subjectIdsDto.getSubjectIds();
		boolean result;
		 if(examIds!=null){
	        List<Integer> ids = new ArrayList();
	        for (String id : examIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + examIds.length);
	        new SubjectDetailsDAO().deleteMultipleSubjects(ids);
	        result = true;
			 return ResultResponse.builder().success(result).build();
	}else{
		    result = false;
			 return ResultResponse.builder().success(result).build();
	  }

	}

	public SubSubjectsResponseDto readListOfSubSubjects(String branchId) {
		
		SubSubjectsResponseDto result = new SubSubjectsResponseDto();
	    try {
	    	List<Subjectmaster> subjectList = new SubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(branchId));
	    	List<SubSubject> subSubjectList = new SubjectDetailsDAO().readListOfSubSubject(Integer.parseInt(branchId));
	    	 Map<Integer, String> subjectIdNameMap = subjectList.stream()
	    	            .collect(Collectors.toMap(
	    	                Subjectmaster::getSubjectid,
	    	                Subjectmaster::getSubjectname
	    	            ));
	    	
	    	 
	    	    Map<String, List<String>> subjectSubSubjectMap = new HashMap<>();

	            for (SubSubject sub : subSubjectList) {
	                String subjectName = subjectIdNameMap.get(sub.getSubjectid());
	                if (subjectName != null) {
	                	subjectSubSubjectMap.computeIfAbsent(subjectName+":"+sub.getSubjectid(), k -> new ArrayList<>())
	                          .add(sub.getSubsubjectname());
	                }
	            }
	            
			result.setSubSubjectMap(subjectSubSubjectMap);
			result.setSuccess(true);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}

	public ResultResponse addSubSubject(SubSubjectDto subSubjectDto, String branchId, String userLoginId) {
		List<SubSubject> subSubjectList = new ArrayList<SubSubject>();
		boolean result= true;
		
		if(branchId!=null){
			String[] subjectNameId = DataUtil.emptyString(subSubjectDto.getSubjectName()).split(":");
			String[] subSubjectNames = subSubjectDto.getSubSubjects();
			
			for(int i=0;i<subSubjectNames.length;i++) {
				SubSubject subSubject = new SubSubject();
				subSubject.setSubjectid(Integer.parseInt(subjectNameId[0]));
				subSubject.setSubsubjectname(subSubjectNames[i]);
				subSubject.setBranchid(Integer.parseInt(branchId));
				subSubject.setUserid(Integer.parseInt(userLoginId));
				subSubjectList.add(subSubject);
			}
			
			result = new SubjectDetailsDAO().addSubSubject(subSubjectList);
			
			return ResultResponse.builder().success(result).build();
		}
		
		return ResultResponse.builder().build();
	}

	public ResultResponse deleteMultipleSubSubject(SubjectIdsDto subjectIdsDto) {
		String[] subIds = subjectIdsDto.getSubjectIds();
		boolean result;
		 if(subIds!=null){
	        List<Integer> ids = new ArrayList();
	        for (String id : subIds) {
	            System.out.println("id" + id);
	            ids.add(Integer.valueOf(id));

	        }
	        System.out.println("id length" + subIds.length);
	        new SubjectDetailsDAO().deleteMultipleSubSubject(ids);
	        result = true;
			return  ResultResponse.builder().success(result).build();
	}else {
			 result = false;
			 return ResultResponse.builder().success(result).build();
		 }
	}

}
