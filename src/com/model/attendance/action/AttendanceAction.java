/**
 * 
 */
package com.model.attendance.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.attendance.service.AttendanceService;
import com.model.employee.service.EmployeeService;

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
		}else if ("uploadAttendanceFile".equalsIgnoreCase(action)) {
			url = uploadAttendanceFile();
		}else if ("searchStudentAttendanceDetails".equalsIgnoreCase(action)) {
			url = searchStudentAttendanceDetails();
		}else if ("viewAttendance".equalsIgnoreCase(action)) {
			url = viewAttendance();
		}else if ("viewAttendanceCenter".equalsIgnoreCase(action)) {
                    url = viewAttendanceCenter();
                }else if ("searchStudentAttendanceDetailsMonthly".equalsIgnoreCase(action)) {
			url = searchStudentAttendanceDetailsMonthly();
		}else if ("updateStudentAttendanceDetails".equalsIgnoreCase(action)) {
			url = updateStudentAttendanceDetails();
		}else if ("searchStudentAttendanceDetailsMonthlyGraph".equalsIgnoreCase(action)) {
			url = searchStudentAttendanceDetailsMonthlyGraph();
		}else if ("searchStudentAttendanceDetailsMark".equalsIgnoreCase(action)) {
			url = searchStudentAttendanceDetailsMark();
		}else if ("markStudentsAttendance".equalsIgnoreCase(action)) {
			url = markStudentsAttendance();
		}else if ("exportMonthlyData".equalsIgnoreCase(action)) {
			url = exportMonthlyData();
		}else if ("download".equalsIgnoreCase(action)) {
			url = download();
		}else if ("viewAttendanceStaff".equalsIgnoreCase(action)) {
			url = viewAttendanceStaff();
		}else if ("searchStaffAttendanceDetails".equalsIgnoreCase(action)) {
			url = searchStaffAttendanceDetails();
		}else if ("updateStaffAttendanceDetails".equalsIgnoreCase(action)) {
			url = updateStaffAttendanceDetails();
		}else if ("searchStaffAttendanceDetailsMonthly".equalsIgnoreCase(action)) {
			url = searchStaffAttendanceDetailsMonthly();
		}else if ("attendanceMarkStaff".equalsIgnoreCase(action)) {
			url = attendanceMarkStaff();
		}else if ("markStaffAttendance".equalsIgnoreCase(action)) {
			url = markStaffAttendance();
		}else if ("attendanceExportViewStaff".equalsIgnoreCase(action)) {
			url = attendanceExportViewStaff();
		}else if ("exportMonthlyDataStaff".equalsIgnoreCase(action)) {
			url = exportMonthlyAttendanceStaff();
		}else if ("downloadStaffAttendance".equalsIgnoreCase(action)) {
			url = downloadStaffAttendance();
		}else if ("markAttendance".equalsIgnoreCase(action)) {
            url = markAttendance();
        }else if ("markAttendanceCenter".equalsIgnoreCase(action)) {
            url = markAttendanceCenter();
        }else if ("getSubjects".equalsIgnoreCase(action)) {
            getSubjects();
        }else if ("printAttendanceReport".equalsIgnoreCase(action)) {
            url = printAttendanceReport();
        }else if ("attendancereport".equalsIgnoreCase(action)) {
            url = AttendanceReport();
        }else if ("attendancereportlist".equalsIgnoreCase(action)) {
            url = AttendanceReportList();
        }else if ("searchStudentAttendanceList".equalsIgnoreCase(action)) {
            url = searchStudentAttendanceList();
        }else if ("searchAttendanceStatus".equalsIgnoreCase(action)) {
            url = searchAttendanceStatus();
        }
		return url;
	}
	
	
	private String AttendanceReportList() {

		if(new AttendanceService(request, response).viewAttendance()){
			return "attendancereportlist.jsp";
		}
		return errorPage;
	}

	private String markAttendanceCenter() {
        new AttendanceService(request, response).markAttendanceCenter();
        return "attendancemark.jsp";
    }

	private String searchAttendanceStatus() {
		new AttendanceService(request, response).searchAttendanceStatus();
		return "attendancereport.jsp";
	}

	private String AttendanceReport() {
		new AttendanceService(request, response).getDetails();
		return "attendancereport.jsp";
	}

	private String viewAttendanceCenter() {
            if(new AttendanceService(request, response).viewAttendanceCenter()){
                    return "viewattendance.jsp";
            }
            return errorPage;
        }

    private String printAttendanceReport() {
	    return "printattendancereport.jsp";
    }

    private void getSubjects() {
	    
	    try {
            new AttendanceService(request, response).getSubjects();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String markAttendance() {
        new AttendanceService(request, response).markAttendance();
        return "attendancemark.jsp";
    }

    private String downloadStaffAttendance() {
		
		if(new AttendanceService(request, response).downloadFileStaff()){
			return "attendanceexportsuccessstaff.jsp";
		}
		return "exportfailure.jsp";
		
	}

	private String exportMonthlyAttendanceStaff() {
		
		if(new AttendanceService(request, response).exportMonthlyDataStaff()){
			return "attendanceexportsuccessstaff.jsp";
		}
		return errorPage;
	}

	private String attendanceExportViewStaff() {
		
		if(new AttendanceService(request, response).viewAttendanceStaff()){
			return "attendanceexportstaff.jsp";
		}
		return errorPage;
	}

	private String markStaffAttendance() {
		
		if(new AttendanceService(request, response).markStaffAttendance()){
			return "attendancemarkstaffsuccess.jsp";
		}
		return errorPage;
	}

	private String attendanceMarkStaff() {

		if(new AttendanceService(request, response).viewAttendanceStaff()){
			return "attendancemarkstaff.jsp";
		}
		return errorPage;
	}

	private String searchStaffAttendanceDetailsMonthly() {
		if(new AttendanceService(request, response).viewStaffAttendanceDetailsMonthly()){
			return "attendanceviewmonthlystaff.jsp";
		}
		return errorPage;
	}

	private String updateStaffAttendanceDetails() {
		if(new AttendanceService(request, response).updateStaffAttendanceDetails()){
			return "attendanceviewstaff.jsp";
		}
		return errorPage;
	}

	private String searchStaffAttendanceDetails() {
		if(new AttendanceService(request, response).searchStaffAttendanceDetails()){
			return "attendanceviewstaff.jsp";
		}
		return errorPage;
	}

	private String viewAttendanceStaff() {

		if(new AttendanceService(request, response).viewAttendanceStaff()){
			return "attendanceviewstaff.jsp";
		}
		return errorPage;
	}

	private String download() {
		if(new AttendanceService(request, response).downloadFile()){
			return "attendanceexportsuccess.jsp";
		}
		return "exportfailure.jsp";
	}

	private String exportMonthlyData() {
		
		if(new AttendanceService(request, response).exportMonthlyData()){
			return "attendanceexportsuccess.jsp";
		}
		return errorPage;
	}

	private String markStudentsAttendance() {
		new AttendanceService(request, response).markStudentsAttendance();
			return "attendancemark.jsp";
	}

	private String searchStudentAttendanceDetailsMark() {
		
	            new AttendanceService(request, response).viewStudentAttendanceDetailsMark();
			return "attendancemark.jsp";
	}

	private String searchStudentAttendanceDetailsMonthlyGraph() {
		
		if(new AttendanceService(request, response).viewStudentAttendanceDetailsMonthlyGraph()){
			return "viewattendancegraph.jsp";
		}
		return errorPage;
	}

	private String updateStudentAttendanceDetails() {
		
		new AttendanceService(request, response).updateStudentAttendanceDetails();
		return "viewattendance.jsp";
		/*if(new AttendanceService(request, response).updateStudentAttendanceDetails()){
			return "updateattendancesuccess.jsp";
		}
		return errorPage;*/
	}

	private String searchStudentAttendanceDetailsMonthly() {
		
		if(new AttendanceService(request, response).viewStudentAttendanceDetailsMonthly()){
			return "viewattendancemonthly.jsp";
		}
		return errorPage;
	}

	private String viewAttendance() {

		if(new AttendanceService(request, response).viewAttendance()){
			return "viewattendance.jsp";
		}
		return errorPage;
	}

	private String searchStudentAttendanceDetails() {
		if(new AttendanceService(request, response).searchStudentAttendanceDetails(true)){
			return "viewattendance.jsp";
		}
		return errorPage;
	}
	
	private String searchStudentAttendanceList() {
		if(new AttendanceService(request, response).searchStudentAttendanceList(true)){
			return "printattendancereport.jsp";
		}
		return errorPage;
	}

	private String uploadAttendanceFile(){
		try {
			if(new AttendanceService(request, response).uploadAttendanceFile()){
				return "studentdailyattendancesuccess.jsp";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorPage;
	}

	private String addStaffAttendanceMaster() {
		if(new AttendanceService(request, response).addStaffAttendanceMaster()){
			return "staffattendancesuccess.jsp";
		}
		return errorPage;
	}

	private String searchEmployees() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		new AttendanceService(request, response).viewAllHolidays();
		new AttendanceService(request, response).viewAllWeekOffs();
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