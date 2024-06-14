package org.ideoholic.curium.model.attendance.action;

import org.ideoholic.curium.model.attendance.dto.*;
import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        StaffAttendanceDetailsDto staffAttendanceDetailsDto = new StaffAttendanceDetailsDto();
        staffAttendanceDetailsDto.setSearchDate(request.getParameter("dateofattendance"));

        StaffAttendanceDetailsResponseDto staffAttendanceDetailsResponseDto = attendanceService.searchStaffAttendanceDetails(staffAttendanceDetailsDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("StaffListAttendance", staffAttendanceDetailsResponseDto.getStaffListAttendance());
        request.setAttribute("StaffDailyAttendanceDate", staffAttendanceDetailsResponseDto.getStaffDailyAttendanceDate());
        request.setAttribute("searchedDate", staffAttendanceDetailsResponseDto.getSearchedDate());
        request.setAttribute("staffList", staffAttendanceDetailsResponseDto.getStaffList());

        return staffAttendanceDetailsResponseDto.isSuccess();
    }

    public boolean viewStudentAttendanceDetailsMark() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        StudentAttendanceDetailsMarkDto attendanceDetailsMarkDto = new StudentAttendanceDetailsMarkDto();
        attendanceDetailsMarkDto.setStudentName(request.getParameter("namesearch"));
        attendanceDetailsMarkDto.setAddClass(request.getParameter("classsearch"));
        attendanceDetailsMarkDto.setAddSec(request.getParameter("secsearch"));

        StudentAttendanceDetailsMarkResponseDto attendanceDetailsMarkResponseDto = attendanceService.viewStudentAttendanceDetailsMark(attendanceDetailsMarkDto,  httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("StudentListAttendance", attendanceDetailsMarkResponseDto.getStudentListAttendance());
        request.setAttribute("attendanceclass", attendanceDetailsMarkResponseDto.getAttendanceClass());
        request.setAttribute("attendanceclasssearch", attendanceDetailsMarkResponseDto.getAttendanceClassSearch());

        return attendanceDetailsMarkResponseDto.isSuccess();
    }

    public boolean viewStudentAttendanceDetailsMonthlyGraph() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        StudentAttendanceGraphDto monthlyGraphDto = new StudentAttendanceGraphDto();
        monthlyGraphDto.setStudentExternalIdGraph(request.getParameter("studentexternalidgraph"));
        monthlyGraphDto.setFromDate((request.getParameter("frommonthlyattendance")));
        monthlyGraphDto.setToDate((request.getParameter("tomonthlyattendance")));
        monthlyGraphDto.setStudentNameGraph(request.getParameter("studentnamegraph"));
        monthlyGraphDto.setAdmNoGraph(request.getParameter("admnograph"));

        StudentAttendanceGraphResponseDto graphResponseDto = attendanceService.viewStudentAttendanceDetailsMonthlyGraph(monthlyGraphDto,  httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("xAxis", graphResponseDto.getXAxis());
        request.setAttribute("yAxis", graphResponseDto.getYAxis());
        request.setAttribute("studentnamegraph", graphResponseDto.getStudentNameGraph());
        request.setAttribute("admnograph", graphResponseDto.getAdmNoGraph());
        request.setAttribute("studentList", graphResponseDto.getStudentList());

        return graphResponseDto.isSuccess();
    }

    public boolean viewStudentAttendanceDetailsMonthly() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        StudentAttendanceMonthlyDto attendanceMonthlyDto = new StudentAttendanceMonthlyDto();
        attendanceMonthlyDto.setStudentExternalId(request.getParameter("studentexternalid"));
        attendanceMonthlyDto.setFromDate(request.getParameter("fromdateofattendance"));
        attendanceMonthlyDto.setToDate(request.getParameter("todateofattendance"));
        attendanceMonthlyDto.setStudentName(request.getParameter("studentname"));
        attendanceMonthlyDto.setAdmNo(request.getParameter("admno"));

        StudentAttendanceMonthlyResponseDto attendanceMonthlyResponseDto = attendanceService.viewStudentAttendanceDetailsMonthly(attendanceMonthlyDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("studentDailyAttendance", attendanceMonthlyResponseDto.getStudentDailyAttendance());
        request.setAttribute("studentname", attendanceMonthlyResponseDto.getStudentName());
        request.setAttribute("admno", attendanceMonthlyResponseDto.getAdmNo());
        request.setAttribute("totalpresent", attendanceMonthlyResponseDto.getTotalPresent());
        request.setAttribute("totalabsent", attendanceMonthlyResponseDto.getTotalAbsent());
        request.setAttribute("studentList", attendanceMonthlyResponseDto.getStudentList());

        return attendanceMonthlyResponseDto.isSuccess();
    }

    public boolean searchStudentAttendanceDetails() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        StudentAttendanceDetailsDto attendanceDetailsDto = new StudentAttendanceDetailsDto();
        attendanceDetailsDto.setStudentName(request.getParameter("namesearch"));
        attendanceDetailsDto.setAddClass(request.getParameter("classsearch"));
        attendanceDetailsDto.setAddSec(request.getParameter("secsearch"));
        attendanceDetailsDto.setSearchDate(request.getParameter("dateofattendance"));

        StudentAttendanceDetailsResponseDto attendanceDetailsResponseDto = attendanceService.searchStudentAttendanceDetails(attendanceDetailsDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("StudentListAttendance", attendanceDetailsResponseDto.getStudentListAttendance());
        request.setAttribute("StudentDailyAttendanceDate", attendanceDetailsResponseDto.getStudentDailyAttendanceDate());
        request.setAttribute("searchedDate", attendanceDetailsResponseDto.getSearchDate());
        request.setAttribute("searchList", attendanceDetailsResponseDto.getStudentList());

        return attendanceDetailsResponseDto.isSuccess();
    }

    public boolean updateStudentAttendanceDetails() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        AttendanceDetailsDto attendanceDetailsDto = new AttendanceDetailsDto();
        attendanceDetailsDto.setAttendanceIds(request.getParameterValues("attandanceIDs"));
        attendanceDetailsDto.setStudentAttendanceStatus(request.getParameterValues("studentAttendanceStatus"));

        ResultResponse resultResponse = attendanceService.updateStudentAttendanceDetails(attendanceDetailsDto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean markStudentsAttendance(){
        AttendanceService attendanceService = new AttendanceService(request, response);

        StudentsAttendanceDto attendanceDto = new StudentsAttendanceDto();
        attendanceDto.setAttendanceIds(request.getParameterValues("externalIDs"));
        attendanceDto.setStudentAttendanceStatus(request.getParameterValues("studentAttendanceStatus"));

        ResultResponse resultResponse = attendanceService.markStudentsAttendance(attendanceDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("attendanceresult", resultResponse.getMessage());

        return resultResponse.isSuccess();
    }

    public boolean addStaffAttendanceMaster() {
        AttendanceService attendanceService = new AttendanceService(request, response);

        StaffAttendanceMasterDto attendanceDto = new StaffAttendanceMasterDto();
        attendanceDto.setStaffId(request.getParameterValues("employeeIDs"));
        attendanceDto.setWeeklyOff(request.getParameterValues("weekoffstaff"));
        attendanceDto.setHolidays(request.getParameterValues("holidaysstaff"));
        attendanceDto.setInTime(request.getParameter("intime"));
        attendanceDto.setOutTime(request.getParameter("outtime"));

        ResultResponse resultResponse = attendanceService.addStaffAttendanceMaster(attendanceDto, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public boolean uploadAttendanceFile() throws IOException {
        AttendanceService attendanceService = new AttendanceService(request, response);

        ResultResponse resultResponse = attendanceService.uploadAttendanceFile(httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }
}
