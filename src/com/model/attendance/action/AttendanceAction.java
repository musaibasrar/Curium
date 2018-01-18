/**
 * 
 */
package com.model.attendance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.service.YearService;
import com.model.adminexpenses.service.AdminService;
import com.model.attendance.service.AttendanceService;
import com.model.employee.service.EmployeeService;
import com.model.feescategory.service.FeesService;
import com.model.sendsms.service.SmsService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class AttendanceAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	String errorPage = "error.jsp";

	public AttendanceAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {

		if ("attendanceConfiguration".equalsIgnoreCase(action)) {
			url = attendanceConfiguration();
		} else if ("viewAllHolidays".equalsIgnoreCase(action)) {
			url = viewAllHolidays();
		}else if ("addHolidays".equalsIgnoreCase(action)) {
			url = addHolidays();
		}else if ("addWeekOff".equalsIgnoreCase(action)) {
			url = addWeekOff();
		}else if ("deleteMultiple".equalsIgnoreCase(action)) {
			url = deleteMultiple();
		}else if ("addStudentAttendanceMaster".equalsIgnoreCase(action)) {
			url = addStudentAttendanceMaster();
		}else if ("addStaffAttendanceMaster".equalsIgnoreCase(action)) {
			url = addStaffAttendanceMaster();
		}else if ("searchEmployees".equalsIgnoreCase(action)) {
			url = searchEmployees();
		}
		return url;
	}

	private String addStaffAttendanceMaster() {
		if(new AttendanceService(request, response).addStaffAttendanceMaster()){
			return "staffattendancesuccess.jsp";
		}
		return errorPage;
	}

	private String searchEmployees() {
		new EmployeeService(request, response).searchEmployee();
		return "attendancemaster.jsp";
	}

	private String addStudentAttendanceMaster() {
		if(new AttendanceService(request, response).addStudentAttendanceMaster()){
			return "studentattendancesuccess.jsp";
		}
		return errorPage;
	}

	private String deleteMultiple() {
		if(new AttendanceService(request, response).deleteMultiple()){
			return viewAllHolidays();
		}
		return errorPage;
	}

	private String addWeekOff() {
		if(new AttendanceService(request, response).addWeekOff()){
			return viewAllHolidays();
		}
		return errorPage;
	}

	private String addHolidays() {
		if(new AttendanceService(request, response).addHolidays()){
			return viewAllHolidays();
		}
		return errorPage;
	}

	private String viewAllHolidays() {
		new AttendanceService(request, response).viewAllHolidays();
		return "holiday.jsp";
	
	}

	private String attendanceConfiguration() {
		new EmployeeService(request, response).viewAllRelations();
		new AttendanceService(request, response).viewAllHolidays();
		new AttendanceService(request, response).viewAllWeekOffs();
		new EmployeeService(request, response).ViewAllEmployee();
	       return "attendancemaster.jsp";
	}

	

}
