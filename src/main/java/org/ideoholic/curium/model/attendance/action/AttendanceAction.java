/**
 * 
 */
package org.ideoholic.curium.model.attendance.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.std.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/AttendanceProcess")
public class AttendanceAction {

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@GetMapping("/attendanceExport")
	public String attendanceExport() {
		new StandardService(request, response).viewClasses();
		return "attendanceexport";
	}

	@GetMapping("/markAttendance")
	public String markAttendance() {
		new StandardService(request, response).viewClasses();
		return "attendancemark";
	}

	@PostMapping("/downloadStaffAttendance")
	public String downloadStaffAttendance() {

		if (new AttendanceService(request, response).downloadFileStaff()) {
			return "attendanceexportsuccessstaff";
		}
		return "exportfailure";
	}

	@PostMapping("/exportMonthlyDataStaff")
	public String exportMonthlyAttendanceStaff() {

		if (new AttendanceService(request, response).exportMonthlyDataStaff()) {
			return "attendanceexportsuccessstaff";
		}
		return "error";
	}

	@GetMapping("/attendanceExportViewStaff")
	public String attendanceExportViewStaff() {

		if (new AttendanceService(request, response).viewAttendanceStaff()) {
			return "attendanceexportstaff";
		}
		return "error";
	}

	@PostMapping("/markStaffAttendance")
	public String markStaffAttendance() {

		if (new AttendanceService(request, response).markStaffAttendance()) {
			return "attendancemarkstaffsuccess";
		}
		return "error";
	}

	@GetMapping("/attendanceMarkStaff")
	public String attendanceMarkStaff() {

		if (new AttendanceService(request, response).viewAttendanceStaff()) {
			return "attendancemarkstaff";
		}
		return "error";
	}

	@PostMapping("/searchStaffAttendanceDetailsMonthly")
	public String searchStaffAttendanceDetailsMonthly() {
		if (new AttendanceService(request, response).viewStaffAttendanceDetailsMonthly()) {
			return "attendanceviewmonthlystaff";
		}
		return "error";
	}

	@PostMapping("/updateStaffAttendanceDetails")
	public String updateStaffAttendanceDetails() {
		if (new AttendanceService(request, response).updateStaffAttendanceDetails()) {
			return "attendanceviewstaff";
		}
		return "error";
	}

	@PostMapping("/searchStaffAttendanceDetails")
	public String searchStaffAttendanceDetails() {
		if (new AttendanceService(request, response).searchStaffAttendanceDetails()) {
			return "attendanceviewstaff";
		}
		return "error";
	}

	@GetMapping("/viewAttendanceStaff")
	public String viewAttendanceStaff() {

		if (new AttendanceService(request, response).viewAttendanceStaff()) {
			return "attendanceviewstaff";
		}
		return "error";
	}

	@PostMapping("/download")
	public String download() {
		if (new AttendanceService(request, response).downloadFile()) {
			return "attendanceexportsuccess";
		}
		return "exportfailure";
	}

	@PostMapping("/exportMonthlyData")
	public String exportMonthlyData() {

		if (new AttendanceService(request, response).exportMonthlyData()) {
			return "attendanceexportsuccess";
		}
		return "error";
	}

	@PostMapping("/markStudentsAttendance")
	public String markStudentsAttendance() {

		if (new AttendanceService(request, response).markStudentsAttendance()) {
			return "attendancemark";
		}
		return "error";
	}

	@PostMapping("/searchStudentAttendanceDetailsMark")
	public String searchStudentAttendanceDetailsMark() {

		if (new AttendanceService(request, response).viewStudentAttendanceDetailsMark()) {
			return "attendancemark";
		}
		return "error";
	}

	@PostMapping("/searchStudentAttendanceDetailsMonthlyGraph")
	public String searchStudentAttendanceDetailsMonthlyGraph() {

		if (new AttendanceService(request, response).viewStudentAttendanceDetailsMonthlyGraph()) {
			return "viewattendancegraph";
		}
		return "error";
	}

	@PostMapping("/updateStudentAttendanceDetails")
	public String updateStudentAttendanceDetails() {
		if (new AttendanceService(request, response).updateStudentAttendanceDetails()) {
			return "viewattendance";
		}
		return "error";
	}

	@PostMapping("/searchStudentAttendanceDetailsMonthly")
	public String searchStudentAttendanceDetailsMonthly() {

		if (new AttendanceService(request, response).viewStudentAttendanceDetailsMonthly()) {
			return "viewattendancemonthly";
		}
		return "error";
	}

	@GetMapping("/viewAttendance")
	public String viewAttendance() {

		if (new AttendanceService(request, response).viewAttendance()) {
			new StandardService(request, response).viewClasses();
			return "viewattendance";
		}
		return "error";
	}

	@PostMapping("/searchStudentAttendanceDetails")
	public String searchStudentAttendanceDetails() {
		if (new AttendanceService(request, response).searchStudentAttendanceDetails()) {
			return "viewattendance";
		}
		return "error";
	}

	@PostMapping("/uploadAttendanceFile")
	public String uploadAttendanceFile() {
		try {
			if (new AttendanceService(request, response).uploadAttendanceFile()) {
				return "studentdailyattendancesuccess";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	@PostMapping("/addStaffAttendanceMaster")
	public String addStaffAttendanceMaster() {
		if (new AttendanceService(request, response).addStaffAttendanceMaster()) {
			return "staffattendancesuccess";
		}
		return "error";
	}

	@PostMapping("/searchEmployees")
	public String searchEmployees() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		new AttendanceService(request, response).viewAllHolidays();
		new AttendanceService(request, response).viewAllWeekOffs();
		return "attendancemaster";
	}

	@PostMapping("/addStudentAttendanceMaster")
	public String addStudentAttendanceMaster() {
		if (new AttendanceService(request, response).addStudentAttendanceMaster()) {
			return "studentattendancesuccess";
		}
		return "error";
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		if (new AttendanceService(request, response).deleteMultiple()) {
			return viewAllHolidays();
		}
		return "error";
	}

	@PostMapping("/addWeekOff")
	public String addWeekOff() {
		if (new AttendanceService(request, response).addWeekOff()) {
			return viewAllHolidays();
		}
		return "error";
	}

	@PostMapping("/addHolidays")
	public String addHolidays() {
		if (new AttendanceService(request, response).addHolidays()) {
			return viewAllHolidays();
		}
		return "error";
	}

	@GetMapping("/viewAllHolidays")
	public String viewAllHolidays() {
		new AttendanceService(request, response).viewAllHolidays();
		return "holiday";
	}

	@GetMapping("/attendanceConfiguration")
	public String attendanceConfiguration() {
		new EmployeeService(request, response).viewAllRelations();
		new AttendanceService(request, response).viewAllHolidays();
		new AttendanceService(request, response).viewAllWeekOffs();
		new EmployeeService(request, response).ViewAllEmployee();
		return "attendancemaster";
	}
}