package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.examdetails.dto.AddExamDto;
import org.ideoholic.curium.model.examdetails.dto.AddScheduleDto;
import org.ideoholic.curium.model.examdetails.dto.ExamIdsDto;
import org.ideoholic.curium.model.examdetails.dto.ExamsListResponseDto;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Service
public class ExamDetailsActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String CURRENTACADEMICYEAR = "currentAcademicYear";

    public Boolean addExam() {
        ExamDetailsService examDetailsService = new ExamDetailsService(request,response);

        AddExamDto result = new AddExamDto();
        result.setExamName( request.getParameter("examname"));

        ResultResponse resultResponse = examDetailsService.addExam(result,httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public boolean readListOfExams() {
        ExamDetailsService examDetailsService = new ExamDetailsService(request,response);

        ExamsListResponseDto result = examDetailsService.readListOfExams(httpSession.getAttribute(BRANCHID).toString());

        httpSession.setAttribute("examdetails", result.getExams());
        return result.isSuccess();
    }
    public boolean deleteMultiple() {
        ExamDetailsService examDetailsService = new ExamDetailsService(request,response);

        ExamIdsDto examIdsDto = new ExamIdsDto();
        examIdsDto.setExamIds(request.getParameterValues("examIDs"));

        ResultResponse resultResponse = examDetailsService.deleteMultiple(examIdsDto);
        return resultResponse.isSuccess();
    }
    public boolean addSchedule() {
        ExamDetailsService examDetailsService = new ExamDetailsService(request,response);

        AddScheduleDto addScheduleDto = new AddScheduleDto();
        addScheduleDto.setSubject(request.getParameterValues("subject"));
        addScheduleDto.setDate(request.getParameterValues("fromdate"));
        addScheduleDto.setStartTime(request.getParameterValues("starttime"));
        addScheduleDto.setEndTime(request.getParameterValues("endtime"));
        addScheduleDto.setClassesSelected(request.getParameterValues("classesselected"));
        addScheduleDto.setAcademicyear(request.getParameter("academicyear"));
        addScheduleDto.setExam(request.getParameter("exam"));

        ResultResponse resultResponse = examDetailsService.addSchedule(addScheduleDto, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }
    
}
