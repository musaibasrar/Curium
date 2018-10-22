package com.model.department.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.department.service.DepartmentService;
import com.model.employee.service.EmployeeService;
import com.model.feescategory.service.FeesService;

public class DepartmentAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public DepartmentAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public String execute(String action, String page) {
		if (action.equalsIgnoreCase("addDepartment")) {
			url = addDepartment();
		}else if (action.equalsIgnoreCase("departmentView")) {
			url = departmentView();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			url = deleteMultiple();
		}
		return url;
	}

	private String deleteMultiple() {
		new DepartmentService(request, response).deleteMultiple();
        return "Controller?process=DepartmentProcess&action=departmentView";
	}

	private String departmentView() {
		new DepartmentService(request, response).viewDepartment();
        return "department.jsp";
	}

	private String addDepartment() {

		new DepartmentService(request, response).addDepartment();
		return "Controller?process=DepartmentProcess&action=departmentView";
	}

}
