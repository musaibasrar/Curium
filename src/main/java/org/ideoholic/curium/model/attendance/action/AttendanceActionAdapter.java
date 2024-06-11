package org.ideoholic.curium.model.attendance.action;

import org.ideoholic.curium.model.attendance.dto.ExportMonthlyDataDto;
import org.ideoholic.curium.model.attendance.dto.MarkStaffAttendanceDto;
import org.ideoholic.curium.model.attendance.dto.UpdateStaffAttendanceDetailsDto;
import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.ideoholic.curium.util.DateUtil;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AttendanceActionAdapter {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String CURRENTACADEMICYEAR = "currentacadamicyear";

    public boolean markStaffAttendance() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        MarkStaffAttendanceDto markStaffAttendanceDto = new MarkStaffAttendanceDto();
        markStaffAttendanceDto.setAttendanceIds(request.getParameterValues("externalIDs"));
        markStaffAttendanceDto.setStaffAttendanceStatus(request.getParameterValues("staffAttendanceStatus"));
        markStaffAttendanceDto.setInTime(request.getParameterValues("intime"));
        markStaffAttendanceDto.setOutTime(request.getParameterValues("outtime"));
        markStaffAttendanceDto.setBranchId(Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
        markStaffAttendanceDto.setCurrentAcademicYear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        ResultResponse resultResponse = attendanceService.markStaffAttendance(markStaffAttendanceDto);

        return resultResponse.isSuccess();
    }

    public boolean updateStaffAttendanceDetails() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        UpdateStaffAttendanceDetailsDto updateStaffAttendanceDetailsDto = new UpdateStaffAttendanceDetailsDto();
        updateStaffAttendanceDetailsDto.setAttendanceIds(request.getParameterValues("attandanceIDs"));
        updateStaffAttendanceDetailsDto.setStudentAttendanceStatus(request.getParameterValues("staffAttendanceStatus"));
        updateStaffAttendanceDetailsDto.setCurrentAcademicYear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        ResultResponse resultResponse = attendanceService.updateStaffAttendanceDetails(updateStaffAttendanceDetailsDto);

        return resultResponse.isSuccess();
    }

    public boolean exportMonthlyData() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        ExportMonthlyDataDto exportMonthlyDataDto = new ExportMonthlyDataDto();
        exportMonthlyDataDto.setAddClass( request.getParameter("classsearch"));
        exportMonthlyDataDto.setAddSec(request.getParameter("secsearch"));
        exportMonthlyDataDto.setMonthOf(DateUtil.dateParserddmmyyyy(request.getParameter("monthof")));
        exportMonthlyDataDto.setBranchId((httpSession.getAttribute(BRANCHID).toString()));
        exportMonthlyDataDto.setCurrentAcademicYear(httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        ResultResponse resultResponse = attendanceService.exportMonthlyData(exportMonthlyDataDto);

        return resultResponse.isSuccess();
    }
}
