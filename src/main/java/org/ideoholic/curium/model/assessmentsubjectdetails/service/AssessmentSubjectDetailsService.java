package org.ideoholic.curium.model.assessmentsubjectdetails.service;

import java.util.ArrayList;
import java.util.List;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.assessmentsubjectdetails.dao.AssessmentSubjectDetailsDAO;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubject;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectIdsDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectMaster;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectsResponseDto;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Service for Assessment Subject Details
 * Duplicated from SubjectDetailsService for independent assessment module
 */
@Slf4j
@Service
public class AssessmentSubjectDetailsService {

	public AssessmentSubjectsResponseDto readListOfAssessmentSubjects(String branchId) {
		AssessmentSubjectsResponseDto result = new AssessmentSubjectsResponseDto();

	    try {
	    	List<AssessmentSubject> list = new AssessmentSubjectDetailsDAO().readAllAssessmentSubjects(Integer.parseInt(branchId));
	        result.setList(list);
			result.setSuccess(true);
	    } catch (Exception e) {
	        log.error("Error reading assessment subjects", e);
			result.setSuccess(false);
	    }
		return result;
	}

	public ResultResponse addAssessmentSubject(AssessmentSubjectDto subjectDto, String branchId, String userLoginId) {
		AssessmentSubject subject = new AssessmentSubject();
		boolean result = true;
		String[] classesCat = subjectDto.getAssessmentClassList();
		
		for(String clsCat : classesCat) {
			String[] subjectNames = subjectDto.getSubjectNameList();
			for (String sub : subjectNames) {
				if(branchId != null){
					String[] subjt = sub.split(":");
					subject.setSubjectname(subjt[0]);
					subject.setSubjectid(Integer.parseInt(subjt[1]));
					subject.setMinrating(Float.parseFloat(subjectDto.getMinRating()));
					subject.setMaxrating(Float.parseFloat(subjectDto.getMaxRating()));
					subject.setAssessmentname(DataUtil.emptyString(subjectDto.getAssessmentName()));
					subject.setAssessmentclass(DataUtil.emptyString(clsCat));
					subject.setBranchid(Integer.parseInt(branchId));
					subject.setUserid(Integer.parseInt(userLoginId));
					subject = new AssessmentSubjectDetailsDAO().addAssessmentSubject(subject);
				}
			}
		}
		
		if(subject != null){
			return ResultResponse.builder().success(result).build();
		}
		
		return ResultResponse.builder().build();
	}

	public ResultResponse deleteMultiple(AssessmentSubjectIdsDto subjectIdsDto) {
		String[] assessmentSubjectIds = subjectIdsDto.getSubjectIds();
		boolean result;
		
		if(assessmentSubjectIds != null){
	        List<Integer> ids = new ArrayList<>();
	        for (String id : assessmentSubjectIds) {
	            log.debug("Assessment subject id: " + id);
	            ids.add(Integer.valueOf(id));
	        }
	        log.debug("id length: " + assessmentSubjectIds.length);
	        new AssessmentSubjectDetailsDAO().deleteMultiple(ids);
	        result = true;
			return ResultResponse.builder().success(result).build();
		} else {
			result = false;
			return ResultResponse.builder().success(result).build();
		}
	}

	public ResultResponse addAssessmentSubjectMaster(AssessmentSubjectDto subjectDto, String branchId, String userLoginId) {
		AssessmentSubjectMaster subject = new AssessmentSubjectMaster();
		boolean result;
		
		if(branchId != null){
			subject.setSubjectname(DataUtil.emptyString(subjectDto.getSubjectName()));
			subject.setCategory(DataUtil.emptyString(subjectDto.getCategory()));
			subject.setBranchid(Integer.parseInt(branchId));
			subject.setUserid(Integer.parseInt(userLoginId));
			subject = new AssessmentSubjectDetailsDAO().addSubjectMaster(subject);
			 
			if(subject == null){
				result = false;
				return ResultResponse.builder().success(result).build();
			}
			result = true;
			return ResultResponse.builder().success(result).build();
		}
		return ResultResponse.builder().build();
	}

	public AssessmentSubjectsResponseDto readListOfSubjectNames(String branchId) {
		AssessmentSubjectsResponseDto result = new AssessmentSubjectsResponseDto();
	    try {
	    	List<AssessmentSubjectMaster> list = new AssessmentSubjectDetailsDAO().readListOfSubjectNames(Integer.parseInt(branchId));
			result.setListSubjectNames(list);
			result.setSuccess(true);
	    } catch (Exception e) {
	        log.error("Error reading assessment subject names", e);
	    }
		return result;
	}

	public ResultResponse deleteMultipleSubjectMaster(AssessmentSubjectIdsDto subjectIdsDto) {
		String[] subjectIds = subjectIdsDto.getSubjectIds();
		boolean result;
		
		if(subjectIds != null){
	        List<Integer> ids = new ArrayList<>();
	        for (String id : subjectIds) {
	            log.debug("Subject master id: " + id);
	            ids.add(Integer.valueOf(id));
	        }
	        new AssessmentSubjectDetailsDAO().deleteMultipleSubjectMaster(ids);
	        result = true;
			return ResultResponse.builder().success(result).build();
		} else {
			result = false;
			return ResultResponse.builder().success(result).build();
		}
	}
}
