package org.ideoholic.curium.model.assessmentsubjectdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectIdsDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectsResponseDto;
import org.ideoholic.curium.model.assessmentsubjectdetails.service.AssessmentSubjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Action Adapter for Assessment Subject Details
 * Handles request/response mapping between web layer and service layer
 */
@Service
public class AssessmentSubjectDetailsActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AssessmentSubjectDetailsService assessmentSubjectDetailsService;

    private static final String BRANCHID = "branchid";
    private static final String USERLOGINID = "userloginid";

    public boolean readListOfAssessmentSubjects() {

        AssessmentSubjectsResponseDto result = assessmentSubjectDetailsService.readListOfAssessmentSubjects(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("listAssessmentSubject", result.getList());
        return result.isSuccess();
    }
    
    public boolean addAssessmentSubject() {

        AssessmentSubjectDto subjectDto = new AssessmentSubjectDto();
        subjectDto.setSubjectNameList(request.getParameterValues("subjectname"));
        subjectDto.setMinRating(request.getParameter("minrating"));
        subjectDto.setMaxRating(request.getParameter("maxrating"));
        subjectDto.setAssessmentName(request.getParameter("assessmentname"));
        subjectDto.setAssessmentClassList(request.getParameterValues("assessmentclass"));
        
        ResultResponse result = assessmentSubjectDetailsService.addAssessmentSubject(
            subjectDto, 
            httpSession.getAttribute(BRANCHID).toString(),
            httpSession.getAttribute(USERLOGINID).toString()
        );
        return result.isSuccess();
    }
    
    public boolean deleteMultiple() {

        AssessmentSubjectIdsDto subjectIdsDto = new AssessmentSubjectIdsDto();
        subjectIdsDto.setSubjectIds(request.getParameterValues("subjectIDs"));

        ResultResponse resultResponse = assessmentSubjectDetailsService.deleteMultiple(subjectIdsDto);

        return resultResponse.isSuccess();
    }
    
    public boolean addAssessmentSubjectMaster() {

        AssessmentSubjectDto subjectDto = new AssessmentSubjectDto();
        subjectDto.setSubjectName(request.getParameter("subjectname"));
        subjectDto.setCategory(request.getParameter("category"));

        ResultResponse resultResponse = assessmentSubjectDetailsService.addAssessmentSubjectMaster(
            subjectDto, 
            httpSession.getAttribute(BRANCHID).toString(),
            httpSession.getAttribute(USERLOGINID).toString()
        );

        return resultResponse.isSuccess();
    }
    
    public boolean deleteMultipleSubjectMaster() {

        AssessmentSubjectIdsDto subjectIdsDto = new AssessmentSubjectIdsDto();
        subjectIdsDto.setSubjectIds(request.getParameterValues("subjectIDs"));

        ResultResponse resultResponse = assessmentSubjectDetailsService.deleteMultipleSubjectMaster(subjectIdsDto);

        return resultResponse.isSuccess();
    }
    
    public void readListOfSubjectNames() {

        AssessmentSubjectsResponseDto result = assessmentSubjectDetailsService.readListOfSubjectNames(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("listAssessmentSubjectNames", result.getListSubjectNames());
    }
}
