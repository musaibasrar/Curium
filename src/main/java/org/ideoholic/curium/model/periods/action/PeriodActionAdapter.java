package org.ideoholic.curium.model.periods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.periods.dto.PeriodDetailsDto;
import org.ideoholic.curium.model.periods.dto.PeriodMasterIdDto;
import org.ideoholic.curium.model.periods.dto.PeriodsSaveDto;
import org.ideoholic.curium.model.periods.dto.TeacherTimeTableResponseDto;
import org.ideoholic.curium.model.periods.dto.TimeTableResponseDto;
import org.ideoholic.curium.model.periods.dto.TimeTableViewResponseDto;
import org.ideoholic.curium.model.periods.service.PeriodService;
import org.ideoholic.curium.util.Constants;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodActionAdapter {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    PeriodService periodService;

    public boolean viewTeacherTimeTable() {

        String teacherName = request.getParameter("teachername");

        TeacherTimeTableResponseDto responseDto = periodService.viewTeacherTimeTable(teacherName, DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
        request.setAttribute("teachername", responseDto.getTeacherName());
        request.setAttribute("teacherperiodmasterlist", responseDto.getPeriodMapList());

        return responseDto.isSuccess();
    }

    public boolean generateTimeTable() {
        TimeTableResponseDto responseDto = periodService.generateTimeTable(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
        httpSession.setAttribute("currentYear", responseDto.getCurrentYear());
        httpSession.setAttribute("periodmasterlist", responseDto.getPeriodMaster());

        return responseDto.isSuccess();
    }

    public boolean deletePeriods() {
        PeriodMasterIdDto dto = new PeriodMasterIdDto();
        dto.setPeriodMasterId(request.getParameterValues("idperiodmaster"));

        ResultResponse resultResponse = periodService.deletePeriods(dto);
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
        dto.setFromClass(request.getParameter("fromclass"));
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

        ResultResponse resultResponse = periodService.savePeriods(dto, DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID), DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID));

        return resultResponse.isSuccess();
    }

    public boolean periodConfiguration() {
        TimeTableResponseDto responseDto = periodService.periodConfiguration(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
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
    	PeriodDetailsDto result = periodService.getPeriodDetail(DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID));
        httpSession.setAttribute("employeeList", result.getEmployeeList());
        httpSession.setAttribute("employeeListProcessSalary", result.getEmployeeListProcessSalary());
        httpSession.setAttribute("listSubjectNames", result.getSubjects());

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
        dto.setFromClass(request.getParameter("classsec"));
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

        ResultResponse resultResponse = periodService.updatenewPeriodDetails(dto, DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.BRANCHID), DataUtil.getSessionAttributeOrElseNull(httpSession, Constants.USERID));

        return resultResponse.isSuccess();
    }
}
