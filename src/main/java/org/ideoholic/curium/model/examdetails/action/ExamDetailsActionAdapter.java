package org.ideoholic.curium.model.examdetails.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.examdetails.dto.*;
import org.ideoholic.curium.model.examdetails.service.ExamDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class ExamDetailsActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ExamDetailsService examDetailsService;

    private String BRANCHID = "branchid";
    private String CURRENTACADEMICYEAR = "currentAcademicYear";

    public Boolean addExam() {

        AddExamDto result = new AddExamDto();
        result.setExamName(request.getParameter("examname"));

        ResultResponse resultResponse = examDetailsService.addExam(result, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public boolean readListOfExams() {

        ExamsListResponseDto result = examDetailsService.readListOfExams(httpSession.getAttribute(BRANCHID).toString());

        httpSession.setAttribute("examdetails", result.getExams());
        return result.isSuccess();
    }

    public boolean deleteMultiple() {

        ExamIdsDto examIdsDto = new ExamIdsDto();
        examIdsDto.setExamIds(request.getParameterValues("examIDs"));

        ResultResponse resultResponse = examDetailsService.deleteMultiple(examIdsDto);
        return resultResponse.isSuccess();
    }

    public boolean addSchedule() {

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

    public boolean getExamSchedule() {

        ExamsScheduleResponseDto result = examDetailsService.getExamSchedule(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("examschedule", result.getExams());

        return result.isSuccess();
    }

    public boolean deleteExamSchedule() {

        ExamIdsDto examIdsDto = new ExamIdsDto();
        examIdsDto.setExamIds(request.getParameterValues("idexamschedule"));

        ResultResponse result = examDetailsService.deleteExamSchedule(examIdsDto);

        return result.isSuccess();
    }

    public boolean getExamScheduleDetails() {

        ExamScheduleDto examScheduleDto = new ExamScheduleDto();
        examScheduleDto.setAcademicYear(request.getParameter("academicyear"));
        examScheduleDto.setClassH(request.getParameter("class"));
        examScheduleDto.setClassAdmno(request.getParameter("classandsec"));
        examScheduleDto.setStudentName(request.getParameter("studentName"));
        examScheduleDto.setExam(request.getParameter("exam"));

        ExamsScheduleResponseDto result = examDetailsService.getExamScheduleDetails(examScheduleDto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("selectedclass", result.getSelectedclass());
        request.setAttribute("selectedexam", result.getSelectedexam());
        request.setAttribute("selectedstudentname", result.getSelectedstudentname());
        request.setAttribute("selectedclassandsec", result.getSelectedclassandsec());
        request.setAttribute("selectedadmissionno", result.getSelectedadmissionno());
        request.setAttribute("examschedules", result.getExamschedules());

        return result.isSuccess();
    }

    public void printPreviewHallTicket() {

        PrintPreviewHallTicketDto printPreviewHallTicketDto = new PrintPreviewHallTicketDto();
        printPreviewHallTicketDto.setExamName(request.getParameterValues("examname"));
        printPreviewHallTicketDto.setClasses(request.getParameterValues("classes"));
        printPreviewHallTicketDto.setSubject(request.getParameterValues("subject"));
        printPreviewHallTicketDto.setDateOfExam(request.getParameterValues("date"));
        printPreviewHallTicketDto.setStartTime(request.getParameterValues("starttime"));
        printPreviewHallTicketDto.setEndTime(request.getParameterValues("endtime"));
        printPreviewHallTicketDto.setClassAndSec(request.getParameter("classandsec"));
        printPreviewHallTicketDto.setAdmNo(request.getParameter("admno"));
        printPreviewHallTicketDto.setStudentName(request.getParameter("studentName"));
        printPreviewHallTicketDto.setAcademicYear(request.getParameter("academicyear"));
        printPreviewHallTicketDto.setClassStudying(request.getParameter("class"));

        HallTicketResponseDto result = examDetailsService.printPreviewHallTicket(printPreviewHallTicketDto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", result.getStudentList());
        request.setAttribute("examname", result.getExamname());
        request.setAttribute("examschedulelist", result.getExamscheduleList());
        request.setAttribute("urlbranchid", result.getUrlbranchid());
    }
}
