package org.ideoholic.curium.model.attendance.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.*;
import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service
public class AttendanceActionAdapter {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    private String BRANCHID = "branchid";
    private String CURRENTACADEMICYEAR = "currentAcademicYear";

    @Autowired
    private AttendanceService attendanceService;

    public boolean markStaffAttendance() {

        MarkStaffAttendanceDto markStaffAttendanceDto = new MarkStaffAttendanceDto();
        markStaffAttendanceDto.setAttendanceIds(request.getParameterValues("externalIDs"));
        markStaffAttendanceDto.setStaffAttendanceStatus(request.getParameterValues("staffAttendanceStatus"));
        markStaffAttendanceDto.setInTime(request.getParameterValues("intime"));
        markStaffAttendanceDto.setOutTime(request.getParameterValues("outtime"));

        ResultResponse resultResponse = attendanceService.markStaffAttendance(markStaffAttendanceDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean updateStaffAttendanceDetails() {

        UpdateStaffAttendanceDetailsDto updateStaffAttendanceDetailsDto = new UpdateStaffAttendanceDetailsDto();
        updateStaffAttendanceDetailsDto.setAttendanceIds(request.getParameterValues("attandanceIDs"));
        updateStaffAttendanceDetailsDto.setStudentAttendanceStatus(request.getParameterValues("staffAttendanceStatus"));

        ResultResponse resultResponse = attendanceService.updateStaffAttendanceDetails(updateStaffAttendanceDetailsDto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean exportMonthlyData() {

        ExportMonthlyDataDto exportMonthlyDataDto = new ExportMonthlyDataDto();
        exportMonthlyDataDto.setAddClass( request.getParameter("classsearch"));
        exportMonthlyDataDto.setAddSec(request.getParameter("secsearch"));
        exportMonthlyDataDto.setMonthOf((request.getParameter("monthof")));


        ResultResponse resultResponse = attendanceService.exportMonthlyData(exportMonthlyDataDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean viewStaffAttendanceDetailsMonthly() {

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

    	StudentAttendanceDetailsMarkDto attendanceDetailsMarkDto = new StudentAttendanceDetailsMarkDto();
        attendanceDetailsMarkDto.setStudentName(request.getParameter("namesearch"));
        attendanceDetailsMarkDto.setAddClass(request.getParameter("classsearch"));
        attendanceDetailsMarkDto.setAddSec(request.getParameter("secsearch"));

        StudentAttendanceDetailsMarkResponseDto attendanceDetailsMarkResponseDto = attendanceService.viewStudentAttendanceDetailsMark(attendanceDetailsMarkDto,  httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("StudentListAttendance", attendanceDetailsMarkResponseDto.getStudentListAttendance());
        request.setAttribute("attendanceclass", attendanceDetailsMarkResponseDto.getAttendanceClass());
        request.setAttribute("attendanceclasssearch", attendanceDetailsMarkResponseDto.getAttendanceClassSearch());
        request.setAttribute("dateofattendanceselected", request.getParameter("dateofattendance"));
        return attendanceDetailsMarkResponseDto.isSuccess();
    }

    public boolean viewStudentAttendanceDetailsMonthlyGraph() {

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

        AttendanceDetailsDto attendanceDetailsDto = new AttendanceDetailsDto();
        attendanceDetailsDto.setAttendanceIds(request.getParameterValues("attandanceIDs"));
        attendanceDetailsDto.setStudentAttendanceStatus(request.getParameterValues("studentAttendanceStatus"));

        ResultResponse resultResponse = attendanceService.updateStudentAttendanceDetails(attendanceDetailsDto, httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean markStudentsAttendance(){

    	StudentsAttendanceDto attendanceDto = new StudentsAttendanceDto();
        attendanceDto.setAttendanceIds(request.getParameterValues("externalIDs"));
        attendanceDto.setStudentAttendanceStatus(request.getParameterValues("studentAttendanceStatus"));
        attendanceDto.setDateofAttendance(DateUtil.simpleDateParser(request.getParameter("dateofattendance")));
        
        ResultResponse resultResponse = attendanceService.markStudentsAttendance(attendanceDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        request.setAttribute("attendanceresult", resultResponse.getMessage());

        return resultResponse.isSuccess();
    }

    public boolean addStaffAttendanceMaster() {

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

        ResultResponse resultResponse = attendanceService.uploadAttendanceFile(httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean addStudentAttendanceMaster() {

        StudentAttendanceMasterDto attendanceDto = new StudentAttendanceMasterDto();
        attendanceDto.setWeeklyOff(request.getParameterValues("weekoff"));
        attendanceDto.setHolidays(request.getParameterValues("holidays"));
        attendanceDto.setInTime(request.getParameter("cutoff"));

        ResultResponse resultResponse = attendanceService.addStudentAttendanceMaster(attendanceDto, httpSession.getAttribute(BRANCHID).toString());

        return resultResponse.isSuccess();
    }

    public void viewAllHolidays() {

        ResultResponse resultResponse = attendanceService.viewAllHolidays( httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        if(resultResponse != null && resultResponse.getResultList() != null){
            request.setAttribute("holidaysList", resultResponse.getResultList());
        }
    }

    public void viewAllWeekOffs() {

        ResultResponse resultResponse = attendanceService.viewAllWeekOffs( httpSession.getAttribute(BRANCHID).toString(),httpSession.getAttribute(CURRENTACADEMICYEAR).toString());
        if(resultResponse != null && resultResponse.getResultList() != null){
            request.setAttribute("weekOffList", resultResponse.getResultList());
        }
    }

    public boolean deleteMultiple() {

        HolidayIdsDto holidayIdsDto = new HolidayIdsDto();
        holidayIdsDto.setIds(request.getParameterValues("holidayid"));

        ResultResponse resultResponse = attendanceService.deleteMultiple(holidayIdsDto);

        return resultResponse.isSuccess();
    }

    public boolean addWeekOff() {

        WeekOffDto weekOffDto = new WeekOffDto();
        weekOffDto.setWeekOff(request.getParameterValues("weekoff"));

        ResultResponse resultResponse = attendanceService.addWeekOff(weekOffDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean addHolidays() {

        HolidaysDto holidaysDto = new HolidaysDto();
        holidaysDto.setFromDate(request.getParameterValues("fromdate"));
        holidaysDto.setToDate(request.getParameterValues("todate"));
        holidaysDto.setHolidayName(request.getParameterValues("holidayname"));

        ResultResponse resultResponse = attendanceService.addHolidays(holidaysDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean exportMonthlyDataStaff() {

        MonthlyDataStaffDto monthlyDataStaffDto = new MonthlyDataStaffDto();
        monthlyDataStaffDto.setMonthOf(request.getParameter("monthof"));

        ResultResponse resultResponse = attendanceService.exportMonthlyDataStaff(monthlyDataStaffDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString());

        return resultResponse.isSuccess();
    }

    public boolean viewAttendanceStaff() {

        ResultResponse resultResponse = attendanceService.viewAttendanceStaff(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("staffList", resultResponse.getResultList());

        return resultResponse.isSuccess();
    }

    public boolean viewAttendance() {

        ResultResponse resultResponse = attendanceService.viewAttendance(httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentList", resultResponse.getResultList());

        return resultResponse.isSuccess();
    }

    public boolean downloadFileStaff() {

        ResultResponse resultResponse = attendanceService.downloadFileStaff();

        return resultResponse.isSuccess();
    }

    public boolean downloadFile() {

        ResultResponse resultResponse = attendanceService.downloadFile();

        return resultResponse.isSuccess();
    }

	public boolean attendanceSummaryReport() {

        StudentAttendanceDetailsDto dto = new StudentAttendanceDetailsDto();
        dto.setDateOfAttendance(request.getParameter("attendancedate"));

        StudentAttendanceMonthlyResponseDto resultResponse = attendanceService.attendanceSummaryReport(dto, httpSession.getAttribute(BRANCHID).toString());
        request.setAttribute("studentAttendanceMap", resultResponse.getClassSecAttendanceList());
        request.setAttribute("present", resultResponse.getTotalPresent());
        request.setAttribute("absent", resultResponse.getTotalAbsent());

        return resultResponse.isSuccess();
    }

    public void sendSMSAbsentees(List<Studentdailyattendance> studentDailyAttendanceList){

        StudentsAttendanceDto dto = new StudentsAttendanceDto();
        dto.setAttendanceClass(request.getParameter("attendanceclass"));

        attendanceService.sendSMSAbsentees(studentDailyAttendanceList, dto, Integer.parseInt(httpSession.getAttribute(BRANCHID).toString()));
    }

	public boolean searchStudentAttendanceDetailsMarkSelectedDate() {
		request.setAttribute("dateofattendanceselected", request.getParameter("dateofattendancemark"));
        return true;
	}
}
