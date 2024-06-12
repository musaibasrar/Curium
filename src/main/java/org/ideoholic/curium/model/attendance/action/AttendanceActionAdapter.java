package org.ideoholic.curium.model.attendance.action;

import org.ideoholic.curium.model.attendance.dto.*;
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
        exportMonthlyDataDto.setMonthOf((request.getParameter("monthof")));


        ResultResponse resultResponse = attendanceService.exportMonthlyData(exportMonthlyDataDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean viewStaffAttendanceDetailsMonthly() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        ViewStaffAttendanceDto attendanceDto = new ViewStaffAttendanceDto();
        attendanceDto.setStaffExternalId(request.getParameter("staffexternalid"));
        attendanceDto.setFromDate(request.getParameter("fromdateofattendance"));
        attendanceDto.setToDate(request.getParameter("todateofattendance"));
        attendanceDto.setNameOfStaff(request.getParameter("nameofstaff"));

        ViewStaffAttendanceResponseDto attendanceResponseDto = attendanceService.viewStaffAttendanceDetailsMonthly(attendanceDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("staffDailyAttendance", attendanceResponseDto.getStaffDailyAttendance());
        request.setAttribute("staffname", attendanceResponseDto.getStaffName());
        request.setAttribute("totalpresent", attendanceResponseDto.getTotalPresent());
        request.setAttribute("totalabsent", attendanceResponseDto.getTotalAbsent());
        request.setAttribute("staffList", attendanceResponseDto.getStaffList());

        return attendanceResponseDto.isSuccess();
    }

    public boolean searchStaffAttendanceDetails() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        SearchStaffAttendanceDetailsDto staffAttendanceDetailsDto = new SearchStaffAttendanceDetailsDto();
        staffAttendanceDetailsDto.setSearchDate(request.getParameter("dateofattendance"));

        SearchStaffAttendanceDetailsResponseDto staffAttendanceDetailsResponseDto = attendanceService.searchStaffAttendanceDetails(staffAttendanceDetailsDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("StaffListAttendance", staffAttendanceDetailsResponseDto.getStaffListAttendance());
        request.setAttribute("StaffDailyAttendanceDate", staffAttendanceDetailsResponseDto.getStaffDailyAttendanceDate());
        request.setAttribute("searchedDate", staffAttendanceDetailsResponseDto.getSearchedDate());
        request.setAttribute("staffList", staffAttendanceDetailsResponseDto.getStaffList());

        return staffAttendanceDetailsResponseDto.isSuccess();
    }
}
