package org.ideoholic.curium.model.assessmentdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.assessmentdetails.dto.*;
import org.ideoholic.curium.model.assessmentdetails.service.HolisticAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Action Adapter for Holistic Development Assessment
 * Handles request/response mapping between web layer and service layer
 */
@Service
public class HolisticAssessmentActionAdapter {
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private HolisticAssessmentService holisticAssessmentService;

    private static final String BRANCHID = "branchid";
    private static final String CURRENTACADEMICYEAR = "currentAcademicYear";

    public Boolean addAssessment() {

        AddAssessmentDto result = new AddAssessmentDto();
        result.setAssessmentName(request.getParameter("assessmentname"));

        ResultResponse resultResponse = holisticAssessmentService.addAssessment(result, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public boolean readListOfAssessments() {

        AssessmentListResponseDto result = holisticAssessmentService.readListOfAssessments(httpSession.getAttribute(BRANCHID).toString());

        httpSession.setAttribute("assessmentdetails", result.getAssessments());
        return result.isSuccess();
    }

    public boolean deleteMultiple() {

        AssessmentIdsDto assessmentIdsDto = new AssessmentIdsDto();
        assessmentIdsDto.setAssessmentIds(request.getParameterValues("assessmentIDs"));

        ResultResponse resultResponse = holisticAssessmentService.deleteMultiple(assessmentIdsDto);
        return resultResponse.isSuccess();
    }

    public boolean addSchedule() {

        AddAssessmentScheduleDto addScheduleDto = new AddAssessmentScheduleDto();
        addScheduleDto.setSubject(request.getParameterValues("subject"));
        addScheduleDto.setDate(request.getParameterValues("fromdate"));
        addScheduleDto.setStartTime(request.getParameterValues("starttime"));
        addScheduleDto.setEndTime(request.getParameterValues("endtime"));
        addScheduleDto.setClassesSelected(request.getParameterValues("classesselected"));
        addScheduleDto.setSectionSelected(request.getParameter("sectionselected"));
        addScheduleDto.setAcademicyear(request.getParameter("academicyear"));
        addScheduleDto.setAssessment(request.getParameter("assessment"));

        ResultResponse resultResponse = holisticAssessmentService.addSchedule(addScheduleDto, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public boolean getAssessmentSchedule() {

        AssessmentScheduleResponseDto result = holisticAssessmentService.getAssessmentSchedule(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("assessmentschedule", result.getAssessmentschedules());

        return result.isSuccess();
    }

    public boolean deleteAssessmentSchedule() {

        AssessmentIdsDto assessmentIdsDto = new AssessmentIdsDto();
        assessmentIdsDto.setAssessmentIds(request.getParameterValues("idassessmentschedule"));

        ResultResponse result = holisticAssessmentService.deleteAssessmentSchedule(assessmentIdsDto);

        return result.isSuccess();
    }
}
