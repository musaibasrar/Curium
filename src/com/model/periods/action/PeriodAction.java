/**
 * 
 */
package com.model.periods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.documents.service.DocumentService;
import com.model.employee.service.EmployeeService;
import com.model.feescategory.service.FeesService;
import com.model.periods.service.PeriodService;
import com.model.stampfees.service.StampFeesService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class PeriodAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	private String error ="error.jsp";

	public PeriodAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {

		if ("periodConfiguration".equalsIgnoreCase(action)) {
			url = periodConfiguration();
		}else if ("savePeriods".equalsIgnoreCase(action)) {
			url = savePeriods();
		}else if ("viewTimeTable".equalsIgnoreCase(action)) {
			url = viewTimeTable();
		}else if ("deletePeriods".equalsIgnoreCase(action)) {
			url = deletePeriods();
		}else if ("generateTimeTable".equalsIgnoreCase(action)) {
			url = generateTimeTable();
		}else if ("generateTeacherTimeTable".equalsIgnoreCase(action)) {
			url = generateTeacherTimeTable();
		}else if ("viewTeacherTimeTable".equalsIgnoreCase(action)) {
			url = viewTeacherTimeTable();
		}
		return url;
	}
	

	private String viewTeacherTimeTable() {
		
		if(new PeriodService(request, response).viewTeacherTimeTable()){
			return "teachertimetableview.jsp";
		}
		return error;
		
	}

	private String generateTeacherTimeTable() {
		
		if(new EmployeeService(request, response).ViewAllEmployee()){
			return "teachertimetable.jsp";
		}
		return error;
	}

	private String generateTimeTable() {
		
		if(new PeriodService(request, response).generateTimeTable()){
			return "classestimetable.jsp";
		}
		return error;
	}

	private String deletePeriods() {
		if(new PeriodService(request, response).deletePeriods()){
			return periodConfiguration();
		}
		
		return error;
	}

	private String viewTimeTable() {
		
		if(new PeriodService(request, response).viewTimeTable()){
			return "timetable.jsp";
		}
		
		return error;
	}

	private String savePeriods() {
		
		if(new PeriodService(request, response).savePeriods()){
			return periodConfiguration();
		}
		return error;
	}

	private String periodConfiguration() {
		
		if(new PeriodService(request, response).periodConfiguration()){
			return "periodmaster.jsp";
		}
		return error;
	}


	private String transferCertificate() {
		if(new DocumentService(request, response).transferCertificate()){
			return "transfercertificate.jsp";
		}
        return error;
	}
}
