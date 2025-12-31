package org.ideoholic.curium.model.attendance.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.attendance.dto.*;
import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.ideoholic.curium.model.employee.dto.EmployeeListDto;
import org.ideoholic.curium.model.employee.dto.EmployeesWithSalaryResponseDto;
import org.ideoholic.curium.model.employee.dto.SearchEmployeeDto;
import org.ideoholic.curium.model.employee.dto.ViewAllRelationsResponseDto;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AttendanceApiActionImpl implements AttendanceApiAction {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private StandardService standardService;
    @Autowired
    private EmployeeService employeeService;

    public ResponseEntity<ResultResponse> attendanceExport(String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> markAttendance(String branchId) {
        ResultResponse result = standardService.viewClasses(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> downloadStaffAttendance() {
        ResultResponse result = attendanceService.downloadFileStaff();
        if (result.isSuccess()) {
            return ResponseEntity.ok().build();
        }
        throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);

    }

    public ResponseEntity<ResultResponse> exportMonthlyAttendanceStaff(MonthlyDataStaffDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.exportMonthlyDataStaff(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> attendanceExportViewStaff(String branchId) {
        ResultResponse result = attendanceService.viewAttendanceStaff(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> markStaffAttendance(MarkStaffAttendanceDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.markStaffAttendance(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> attendanceMarkStaff(String branchId) {
        ResultResponse result = attendanceService.viewAttendanceStaff(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ViewStaffAttendanceResponseDto> searchStaffAttendanceDetailsMonthly(ViewStaffAttendanceDto dto, String branchId, String currentAcademicYear) {
        ViewStaffAttendanceResponseDto result = attendanceService.viewStaffAttendanceDetailsMonthly(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> updateStaffAttendanceDetails(UpdateStaffAttendanceDetailsDto dto, String currentAcademicYear) {
        ResultResponse result = attendanceService.updateStaffAttendanceDetails(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<StaffAttendanceDetailsResponseDto> searchStaffAttendanceDetails(StaffAttendanceDetailsDto dto, String branchId, String currentAcademicYear) {
        StaffAttendanceDetailsResponseDto result = attendanceService.searchStaffAttendanceDetails(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> viewAttendanceStaff(String branchId) {
        ResultResponse result = attendanceService.viewAttendanceStaff(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> download() {
        ResultResponse result = attendanceService.downloadFile();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
    }

    public ResponseEntity<ResultResponse> exportMonthlyData(ExportMonthlyDataDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.exportMonthlyData(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> markStudentsAttendance(StudentsAttendanceDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.markStudentsAttendance(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<StudentAttendanceDetailsMarkResponseDto> searchStudentAttendanceDetailsMark(StudentAttendanceDetailsMarkDto dto, String branchId, String currentAcademicYear) {
        StudentAttendanceDetailsMarkResponseDto result = attendanceService.viewStudentAttendanceDetailsMark(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<StudentAttendanceGraphResponseDto> searchStudentAttendanceDetailsMonthlyGraph(StudentAttendanceGraphDto attendanceGraphDto, String branchId, String currentAcademicYear) {
        StudentAttendanceGraphResponseDto result = attendanceService.viewStudentAttendanceDetailsMonthlyGraph(attendanceGraphDto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> updateStudentAttendanceDetails(AttendanceDetailsDto dto, String currentAcademicYear) {
        ResultResponse result = attendanceService.updateStudentAttendanceDetails(dto, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<StudentAttendanceMonthlyResponseDto> searchStudentAttendanceDetailsMonthly(StudentAttendanceMonthlyDto dto, String branchId, String currentAcademicYear) {
        StudentAttendanceMonthlyResponseDto result = attendanceService.viewStudentAttendanceDetailsMonthly(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> viewAttendance(String branchId) {
        ResultResponse result = attendanceService.viewAttendance(branchId);
        if (result.isSuccess()) {
            standardService.viewClasses(branchId);
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<StudentAttendanceDetailsResponseDto> searchStudentAttendanceDetails(StudentAttendanceDetailsDto dto, String branchId, String currentAcademicYear) {
        StudentAttendanceDetailsResponseDto result = attendanceService.searchStudentAttendanceDetails(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<ResultResponse> uploadAttendanceFile(String branchId, String currentAcademicYear) {
        try {
            ResultResponse result = attendanceService.uploadAttendanceFile(branchId, currentAcademicYear);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> addStaffAttendanceMaster(StaffAttendanceMasterDto dto, String branchId) {
        ResultResponse result = attendanceService.addStaffAttendanceMaster(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> addStudentAttendanceMaster(StudentAttendanceMasterDto dto, String branchId) {
        ResultResponse result = attendanceService.addStudentAttendanceMaster(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<EmployeesSearchDto> searchEmployees(SearchEmployeeDto searchEmployeeDto, String branchId, String currentAcademicYear) {
        EmployeesSearchDto result = new EmployeesSearchDto();

        EmployeeListDto employeeListResult = employeeService.searchEmployee(searchEmployeeDto, branchId);
        result.setEmployeeList(employeeListResult.getEmployeeList());

        ViewAllRelationsResponseDto viewAllRelationsResult = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResult.getListDepartment());
        result.setListPosition(viewAllRelationsResult.getListPosition());

        ResultResponse viewAllHolidaysResult = attendanceService.viewAllHolidays(branchId, currentAcademicYear);
        result.setHolidaysmasterList(viewAllHolidaysResult.getResultList());

        ResultResponse viewAllWeekOffsResult = attendanceService.viewAllWeekOffs(branchId, currentAcademicYear);
        result.setWeekOffList(viewAllWeekOffsResult.getResultList());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> deleteMultiple(HolidayIdsDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.deleteMultiple(dto);
        if (result.isSuccess()) {
            return viewAllHolidays(branchId, currentAcademicYear);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> addWeekOff(WeekOffDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.addWeekOff(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return viewAllHolidays(branchId, currentAcademicYear);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> addHolidays(HolidaysDto dto, String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.addHolidays(dto, branchId, currentAcademicYear);
        if (result.isSuccess()) {
            return viewAllHolidays(branchId, currentAcademicYear);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> viewAllHolidays(String branchId, String currentAcademicYear) {
        ResultResponse result = attendanceService.viewAllHolidays(branchId, currentAcademicYear);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<EmployeesSearchDto> attendanceConfiguration(String branchId, String currentAcademicYear) {
        EmployeesSearchDto result = new EmployeesSearchDto();

        ViewAllRelationsResponseDto viewAllRelationsResult = employeeService.viewAllRelations(branchId);
        result.setListDepartment(viewAllRelationsResult.getListDepartment());
        result.setListPosition(viewAllRelationsResult.getListPosition());

        ResultResponse viewAllHolidaysResult = attendanceService.viewAllHolidays(branchId, currentAcademicYear);
        result.setHolidaysmasterList(viewAllHolidaysResult.getResultList());

        ResultResponse viewAllWeekOffsResult = attendanceService.viewAllWeekOffs(branchId, currentAcademicYear);
        result.setWeekOffList(viewAllWeekOffsResult.getResultList());

        EmployeesWithSalaryResponseDto employeesWithSalaryResult = employeeService.ViewAllEmployee(branchId);
        result.setEmployeeListProcessSalary(employeesWithSalaryResult.getEmployeeListProcessSalary());
        result.setEmployeeList(employeesWithSalaryResult.getEmployeeList());

        return ResponseEntity.ok(result);
    }

    public String attendanceReport() {
        return "attendancesummaryreport";
    }

    public ResponseEntity<StudentAttendanceMonthlyResponseDto> showReports(StudentAttendanceDetailsDto dto, String branchId) {

        StudentAttendanceMonthlyResponseDto result = attendanceService.attendanceSummaryReport(dto, branchId);
        return ResponseEntity.ok(result);
    }

}
