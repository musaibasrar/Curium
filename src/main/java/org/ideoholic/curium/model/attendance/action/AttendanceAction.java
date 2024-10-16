/**
 * 
 */
package org.ideoholic.curium.model.attendance.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.attendance.service.AttendanceService;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.std.action.StandardAction;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
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

	@Autowired
	HttpSession httpSession;

	@Autowired
	private AttendanceActionAdapter attendanceActionAdapter;

	@Autowired
	private StandardActionAdapter standardActionAdapter;
	@Autowired
	private EmployeeActionAdapter employeeActionAdapter;

	String errorPage = "error";

	//TODO:To be migrated after the StandardAction viewClasses() and StandardService class.
	@GetMapping("/attendanceExport")
	public String attendanceExport() {
		standardActionAdapter.viewClasses();
		return "attendanceexport";
	}

	//TODO:To be migrated after the StandardAction viewClasses() and StandardService class.
	@GetMapping("/markAttendance")
	public String markAttendance() {
		standardActionAdapter.viewClasses();
		return "attendancemark";
	}

	@PostMapping("/downloadStaffAttendance")
	public String downloadStaffAttendance() {

		if (attendanceActionAdapter.downloadFileStaff()) {
			return "attendanceexportsuccessstaff";
		}
		return "exportfailure";

	}

	@PostMapping("/exportMonthlyDataStaff")
	public String exportMonthlyAttendanceStaff() {

		if (attendanceActionAdapter.exportMonthlyDataStaff()) {
			return "attendanceexportsuccessstaff";
		}
		return errorPage;
	}

	@GetMapping("/attendanceExportViewStaff")
	public String attendanceExportViewStaff() {

		if (attendanceActionAdapter.viewAttendanceStaff()) {
			return "attendanceexportstaff";
		}
		return errorPage;
	}

	@PostMapping("/markStaffAttendance")
	public String markStaffAttendance() {

		if (attendanceActionAdapter.markStaffAttendance()) {
			return "attendancemarkstaffsuccess";
		}
		return errorPage;
	}

	@GetMapping("/attendanceMarkStaff")
	public String attendanceMarkStaff() {

		if (attendanceActionAdapter.viewAttendanceStaff()) {
			return "attendancemarkstaff";
		}
		return errorPage;
	}

	@PostMapping("/searchStaffAttendanceDetailsMonthly")
	public String searchStaffAttendanceDetailsMonthly() {
		if (attendanceActionAdapter.viewStaffAttendanceDetailsMonthly()) {
			return "attendanceviewmonthlystaff";
		}
		return errorPage;
	}

	@PostMapping("/updateStaffAttendanceDetails")
	public String updateStaffAttendanceDetails() {
		if (attendanceActionAdapter.updateStaffAttendanceDetails()) {
			return "attendanceviewstaff";
		}
		return errorPage;
	}

	@PostMapping("/searchStaffAttendanceDetails")
	public String searchStaffAttendanceDetails() {
		if (attendanceActionAdapter.searchStaffAttendanceDetails()) {
			return "attendanceviewstaff";
		}
		return errorPage;
	}

	@GetMapping("/viewAttendanceStaff")
	public String viewAttendanceStaff() {

		if (attendanceActionAdapter.viewAttendanceStaff()) {
			return "attendanceviewstaff";
		}
		return errorPage;
	}

	@PostMapping("/download")
	public String download() {
		if (attendanceActionAdapter.downloadFile()) {
			return "attendanceexportsuccess";
		}
		return "exportfailure";
	}

	@PostMapping("/exportMonthlyData")
	public String exportMonthlyData() {

		if (attendanceActionAdapter.exportMonthlyData()) {
			return "attendanceexportsuccess";
		}
		return errorPage;
	}

	@PostMapping("/markStudentsAttendance")
	public String markStudentsAttendance() {

		if (attendanceActionAdapter.markStudentsAttendance()) {
			return "attendancemark";
		}
		return errorPage;
	}

	@PostMapping("/searchStudentAttendanceDetailsMark")
	public String searchStudentAttendanceDetailsMark() {

		if (attendanceActionAdapter.viewStudentAttendanceDetailsMark()) {
			return "attendancemark";
		}
		return errorPage;
	}

	@PostMapping("/searchStudentAttendanceDetailsMonthlyGraph")
	public String searchStudentAttendanceDetailsMonthlyGraph() {

		if (attendanceActionAdapter.viewStudentAttendanceDetailsMonthlyGraph()) {
			return "viewattendancegraph";
		}
		return errorPage;
	}

	@PostMapping("/updateStudentAttendanceDetails")
	public String updateStudentAttendanceDetails() {
		if (attendanceActionAdapter.updateStudentAttendanceDetails()) {
			return "viewattendance";
		}
		return errorPage;
	}

	@PostMapping("/searchStudentAttendanceDetailsMonthly")
	public String searchStudentAttendanceDetailsMonthly() {

		if (attendanceActionAdapter.viewStudentAttendanceDetailsMonthly()) {
			return "viewattendancemonthly";
		}
		return errorPage;
	}

	//TODO:To be migrated after the StandardAction viewClasses() and StandardService class.
	@GetMapping("/viewAttendance")
	public String viewAttendance() {

		if (attendanceActionAdapter.viewAttendance()) {
			standardActionAdapter.viewClasses();
			return "viewattendance";
		}
		return errorPage;
	}

	@PostMapping("/searchStudentAttendanceDetails")
	public String searchStudentAttendanceDetails() {
		if (attendanceActionAdapter.searchStudentAttendanceDetails()) {
			return "viewattendance";
		}
		return errorPage;
	}

	@PostMapping("/uploadAttendanceFile")
	public String uploadAttendanceFile() {
		try {
			if (attendanceActionAdapter.uploadAttendanceFile()) {
				return "studentdailyattendancesuccess";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorPage;
	}

	@PostMapping("/addStaffAttendanceMaster")
	public String addStaffAttendanceMaster() {
		if (attendanceActionAdapter.addStaffAttendanceMaster()) {
			return "staffattendancesuccess";
		}
		return errorPage;
	}

	@PostMapping("/addStudentAttendanceMaster")
	public String addStudentAttendanceMaster() {
		if (attendanceActionAdapter.addStudentAttendanceMaster()) {
			return "studentattendancesuccess";
		}
		return errorPage;
	}

	@PostMapping("/searchEmployees")
	public String searchEmployees() {
		employeeActionAdapter.searchEmployee();
		employeeActionAdapter.viewAllRelations();
		attendanceActionAdapter.viewAllHolidays();
		attendanceActionAdapter.viewAllWeekOffs();
		return "attendancemaster";
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		if (attendanceActionAdapter.deleteMultiple()) {
			return viewAllHolidays();
		}
		return errorPage;
	}

	@PostMapping("/addWeekOff")
	public String addWeekOff() {
		if (attendanceActionAdapter.addWeekOff()) {
			return viewAllHolidays();
		}
		return errorPage;
	}

	@PostMapping("/addHolidays")
	public String addHolidays() {
		if (attendanceActionAdapter.addHolidays()) {
			return viewAllHolidays();
		}
		return errorPage;
	}

	@GetMapping("/viewAllHolidays")
	public String viewAllHolidays() {
		attendanceActionAdapter.viewAllHolidays();
		return "holiday";

	}

	@GetMapping("/attendanceConfiguration")
	public String attendanceConfiguration() {
		employeeActionAdapter.viewAllRelations();
		attendanceActionAdapter.viewAllHolidays();
		attendanceActionAdapter.viewAllWeekOffs();
		employeeActionAdapter.ViewAllEmployee();
		return "attendancemaster";
	}
	
	@GetMapping("/attendanceReport")
	public String attendanceReport() {
		return "attendancesummaryreport";
	}
	
	@PostMapping("/attendanceSummaryReport")
	public String showReports() {
		attendanceActionAdapter.attendanceSummaryReport();
		return "attendancesummaryreport";
	}
	
}
