package org.ideoholic.curium.model.attendance.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.attendance.dto.*;
import org.ideoholic.curium.model.employee.dto.SearchEmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/attendanceProcess")
public interface AttendanceApiAction {

    @GetMapping("/attendanceExport")
    ResponseEntity<ResultResponse> attendanceExport( @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/markAttendance")
    ResponseEntity<ResultResponse> markAttendance(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/downloadStaffAttendance")
    ResponseEntity<ResultResponse> downloadStaffAttendance();

    @PostMapping("/exportMonthlyDataStaff")
    ResponseEntity<ResultResponse> exportMonthlyAttendanceStaff(@RequestBody MonthlyDataStaffDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @GetMapping("/attendanceExportViewStaff")
    ResponseEntity<ResultResponse> attendanceExportViewStaff(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/markStaffAttendance")
    ResponseEntity<ResultResponse> markStaffAttendance(@RequestBody MarkStaffAttendanceDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @GetMapping("/attendanceMarkStaff")
    ResponseEntity<ResultResponse> attendanceMarkStaff(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/searchStaffAttendanceDetailsMonthly")
    ResponseEntity<ViewStaffAttendanceResponseDto> searchStaffAttendanceDetailsMonthly(@RequestBody ViewStaffAttendanceDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/updateStaffAttendanceDetails")
    ResponseEntity<ResultResponse> updateStaffAttendanceDetails(@RequestBody UpdateStaffAttendanceDetailsDto dto, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/searchStaffAttendanceDetails")
    ResponseEntity<StaffAttendanceDetailsResponseDto> searchStaffAttendanceDetails(@RequestBody StaffAttendanceDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @GetMapping("/viewAttendanceStaff")
    ResponseEntity<ResultResponse> viewAttendanceStaff(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/download")
    ResponseEntity<ResultResponse> download();

    @PostMapping("/exportMonthlyData")
    ResponseEntity<ResultResponse> exportMonthlyData(@RequestBody ExportMonthlyDataDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/markStudentsAttendance")
    ResponseEntity<ResultResponse> markStudentsAttendance(@RequestBody StudentsAttendanceDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/searchStudentAttendanceDetailsMark")
    ResponseEntity<StudentAttendanceDetailsMarkResponseDto> searchStudentAttendanceDetailsMark(@RequestBody StudentAttendanceDetailsMarkDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/searchStudentAttendanceDetailsMonthlyGraph")
    ResponseEntity<StudentAttendanceGraphResponseDto> searchStudentAttendanceDetailsMonthlyGraph(@RequestBody StudentAttendanceGraphDto attendanceGraphDto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/updateStudentAttendanceDetails")
    ResponseEntity<ResultResponse> updateStudentAttendanceDetails(@RequestBody AttendanceDetailsDto dto, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/searchStudentAttendanceDetailsMonthly")
    ResponseEntity<StudentAttendanceMonthlyResponseDto> searchStudentAttendanceDetailsMonthly(@RequestBody StudentAttendanceMonthlyDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @GetMapping("/viewAttendance")
    ResponseEntity<ResultResponse> viewAttendance(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/searchStudentAttendanceDetails")
    ResponseEntity<StudentAttendanceDetailsResponseDto> searchStudentAttendanceDetails(@RequestBody StudentAttendanceDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/uploadAttendanceFile")
    ResponseEntity<ResultResponse> uploadAttendanceFile(@RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/addStaffAttendanceMaster")
    ResponseEntity<ResultResponse> addStaffAttendanceMaster(@RequestBody StaffAttendanceMasterDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/addStudentAttendanceMaster")
    ResponseEntity<ResultResponse> addStudentAttendanceMaster(@RequestBody StudentAttendanceMasterDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/searchEmployees")
    ResponseEntity<EmployeesSearchDto> searchEmployees(@RequestBody SearchEmployeeDto searchEmployeeDto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/deleteMultiple")
    ResponseEntity<ResultResponse> deleteMultiple(@RequestBody HolidayIdsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/addWeekOff")
    ResponseEntity<ResultResponse> addWeekOff(@RequestBody WeekOffDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/addHolidays")
    ResponseEntity<ResultResponse> addHolidays(@RequestBody HolidaysDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @GetMapping("/viewAllHolidays")
    ResponseEntity<ResultResponse> viewAllHolidays(@RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @GetMapping("/attendanceConfiguration")
    ResponseEntity<EmployeesSearchDto> attendanceConfiguration(@RequestHeader(value = "branchid") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear);

    @PostMapping("/attendanceSummaryReport")
    ResponseEntity<StudentAttendanceMonthlyResponseDto> showReports(@RequestBody StudentAttendanceDetailsDto dto, @RequestHeader(value = "branchid") String branchId);

}
