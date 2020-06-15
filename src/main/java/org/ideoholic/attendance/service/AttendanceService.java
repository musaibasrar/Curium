package org.ideoholic.attendance.service;

import java.io.IOException;
import java.util.Date;

public interface AttendanceService {

	String viewAllHolidays(String currentAcademicYear, String branchId);

	String addHolidays(String[] fromDate, String[] toDate, String[] holidayName, String currentAcademicYear,
		            	String branchId);

	String addWeekOff(String[] weekOff, String currentAcademicYear, String branchId);

	String deleteMultiple(String[] ids);

	String addStudentAttendanceMaster(String[] weeklyOff, String[] holidays, String branchId, String cutOff);

	String addStaffAttendanceMaster(String[] staffId, String[] weekOff, String[] holidays, String inTime,
		                         	String outTime, String branchId);

	String uploadAttendanceFile(String currentAcademicYear, String branchId) throws IOException;

	String searchStudentAttendanceDetails(String currentAcademicYear, String branchId, String studentName,
			                              String addClass, String addSec, String dateOfAttendance);

	String viewAttendance(String branchId);

	String updateStudentAttendanceDetails(String currentAcademicYear, String[] attendanceIds,
			                              String[] studentAttendanceStatus);

	String markStudentsAttendance(String currentAcademicYear, String branchId, String[] attendanceIds,
			String[] studentAttendanceStatus, String attendanceClass);

	String exportMonthlyData(String currentAcademicYear, String branchId, String addClass, String addSec, Date monthOf);

	String downloadFile(byte[] buffer);

	String viewAttendanceStaff(String branchId);

	String searchStaffAttendanceDetails(String currentAcademicYear, String branchId, String dateOfAttendance);

	String updateStaffAttendanceDetails(String currentAcademicYear, String[] attendanceIds,
			String[] studentAttendanceStatus);

	String viewStaffAttendanceDetailsMonthly(String currentAcademicYear, String staffExternalId,String branchId,
			String fromdateofattendance, String todateofattendance, String nameofstaff);

	String markStaffAttendance(String currentAcademicYear, String branchId, String[] attendanceIds,
			String[] staffAttendanceStatus, String inTime, String outTime);

	String exportMonthlyDataStaff(String currentAcademicYear, String branchId, Date monthOf);

}
