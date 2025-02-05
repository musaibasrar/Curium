package org.ideoholic.curium.model.periods.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.periods.dto.*;
import org.ideoholic.curium.model.periods.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class PeriodActionAdapter {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private PeriodService periodService;

    private String BRANCHID = "branchid";
    private String USERID = "userloginid";

    public boolean viewTeacherTimeTable() {


        String teacherName = request.getParameter("teachername");

        TeacherTimeTableResponseDto responseDto = periodService.viewTeacherTimeTable(teacherName, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("teachername", responseDto.getTeacherName());
        request.setAttribute("teacherperiodmasterlist", responseDto.getPeriodMapList());

        return responseDto.isSuccess();
    }

    public boolean generateTimeTable() {

        TimeTableResponseDto responseDto = periodService.generateTimeTable(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("currentYear", responseDto.getCurrentYear());
        httpSession.setAttribute("periodmasterlist", responseDto.getPeriodMaster());

        return responseDto.isSuccess();
    }

    public boolean deletePeriods() {

        PeriodMasterIdDto dto = new PeriodMasterIdDto();
        dto.setPeriodMasterId(request.getParameterValues("idperiodmaster"));

        TimeTableResponseDto resultResponse = periodService.deletePeriods(dto);
        return resultResponse.isSuccess();
    }

    public boolean viewTimeTable() {

        String periodMasterId = request.getParameter("id");

        TimeTableViewResponseDto responseDto = periodService.viewTimeTable(periodMasterId);
        request.setAttribute("timetable", responseDto.getPeriodMaster());
        request.setAttribute("timetableperioddetails", responseDto.getPeriodDetails());
        request.setAttribute("periodmap", responseDto.getPeriodMap());
        request.setAttribute("periodMasterid", responseDto.getPeriodMasterId());

        return responseDto.isSuccess();
    }

    public boolean savePeriods() {

        PeriodsSaveDto dto = new PeriodsSaveDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setTotalNoOfPeriods(request.getParameter("totalperiods"));
        dto.setDurationOfPeriodsHr(request.getParameter("periodduration"));
        dto.setDurationOfPeriodsMin(request.getParameter("perioddurationmin"));
        dto.setDayStartTimeHr(request.getParameter("daystarttime"));
        dto.setDayStartTimeMin(request.getParameter("daystartminutes"));
        dto.setDayStartAm(request.getParameter("daystartam"));
        dto.setDayEndTimeHr(request.getParameter("dayendtime"));
        dto.setDayEndTimeMin(request.getParameter("dayendminutes"));
        dto.setDayEndAm(request.getParameter("dayendam"));
        dto.setFromClass(request.getParameterValues("fromclass"));
        dto.setToClass(request.getParameter("toclass"));

        dto.setPeriods(request.getParameterValues("periods"));
        dto.setSubjects(request.getParameterValues("subject"));
        dto.setStaff(request.getParameterValues("staff"));
        dto.setPeriodStartTimeHr(request.getParameterValues("periodstarttimehr"));
        dto.setPeriodStartTimeMin(request.getParameterValues("periodstarttimemin"));
        dto.setPeriodEndTimeAm(request.getParameterValues("periodstarttimeam"));
        dto.setPeriodEndTimeHr(request.getParameterValues("periodendtimehr"));
        dto.setPeriodEndTimeMin(request.getParameterValues("periodendtimemin"));
        dto.setPeriodEndTimeAm(request.getParameterValues("periodendtimeam"));
        dto.setDays(request.getParameterValues("days"));

        TimeTableResponseDto resultResponse = periodService.savePeriods(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());

        return resultResponse.isSuccess();
    }

    public boolean periodConfiguration() {

        TimeTableResponseDto responseDto = periodService.periodConfiguration(httpSession.getAttribute(BRANCHID).toString());
        httpSession.setAttribute("currentYear", responseDto.getCurrentYear());
        request.setAttribute("periodmasterlist", responseDto.getPeriodMaster());

        return responseDto.isSuccess();
    }

    public void updatePeriodDetails() {

        String periodMasterId = request.getParameter("id");

        TimeTableViewResponseDto responseDto = periodService.updatePeriodDetails(periodMasterId);
        request.setAttribute("periodMasterid", responseDto.getPeriodMasterId());
        request.setAttribute("timetable", responseDto.getPeriodMaster());
        request.setAttribute("timetableperioddetails", responseDto.getPeriodDetails());
        request.setAttribute("periodmap", responseDto.getPeriodMap());
    }

    public void getPeriodDetail() {

        ResultResponse resultResponse = periodService.getPeriodDetail(httpSession.getAttribute(BRANCHID).toString());

    }

    public boolean updatenewPeriodDetails() {

        PeriodsSaveDto dto = new PeriodsSaveDto();
        dto.setAcademicYear(request.getParameter("academicyear"));
        dto.setTotalNoOfPeriods(request.getParameter("totalperiods"));
        dto.setDayStartTimeHr(request.getParameter("daystarttime"));
        dto.setDayStartTimeMin(request.getParameter("daystartminutes"));
        dto.setDayStartAm(request.getParameter("daystartam"));
        dto.setDayEndTimeHr(request.getParameter("dayendtime"));
        dto.setDayEndTimeMin(request.getParameter("dayendminutes"));
        dto.setDayEndAm(request.getParameter("dayendam"));
        dto.setPeriodMasterId(request.getParameter("periodmasterid"));
        dto.setFromClass(request.getParameterValues("classsec"));
        dto.setToClass(request.getParameter("section"));

        dto.setPeriods(request.getParameterValues("periods"));
        dto.setPeriodId(request.getParameterValues("periodid"));
        dto.setSubjects(request.getParameterValues("subject"));
        dto.setStaff(request.getParameterValues("staff"));
        dto.setPeriodStartTimeHr(request.getParameterValues("periodstarttimehr"));
        dto.setPeriodStartTimeMin(request.getParameterValues("periodstarttimemin"));
        dto.setPeriodStartTimeAm(request.getParameterValues("periodstarttimeam"));
        dto.setPeriodEndTimeHr(request.getParameterValues("periodendtimehr"));
        dto.setPeriodEndTimeMin(request.getParameterValues("periodendtimemin"));
        dto.setPeriodEndTimeAm(request.getParameterValues("periodendtimeam"));
        dto.setDays(request.getParameterValues("days"));

        ResultResponse resultResponse = periodService.updatenewPeriodDetails(dto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(USERID).toString());

        return resultResponse.isSuccess();
    }
}
