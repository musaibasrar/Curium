package com.model.employee.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.department.service.DepartmentService;
import com.model.employee.service.EmployeeService;
import com.model.position.service.PositionService;
import com.model.student.service.StudentService;

public class EmployeeAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public EmployeeAction(HttpServletRequest request,
			HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public String execute(String action, String page) {
		if (action.equalsIgnoreCase("addEmployee")) {
			System.out.println("Action is add position");
			url = addEmployee();
		} else if (action.equalsIgnoreCase("ViewAllEmployee")) {
			System.out.println("Action is ViewEmployee");
			url = viewEmployee();
		}else if (action.equalsIgnoreCase("ViewDetails")) {
			System.out.println("Action is ViewDetails");
			url = viewDetails();
		}else if (action.equalsIgnoreCase("updateEmployeeDetails")) {
			System.out.println("Action is updateEmployeeDetails");
			url = updateEmployeeDetails();
		}else if (action.equalsIgnoreCase("updateEmployee")) {
			System.out.println("Action is updateEmployee");
			url = updateEmployee();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			System.out.println("Action is deleteMultiple");
			url = deleteMultiple();
		}else if (action.equalsIgnoreCase("addEmployeePage")) {
			System.out.println("Action is addEmployeePage");
			url = addEmployeePage();
		}
		return url;
	}

	private String addEmployeePage() {
		
		new EmployeeService(request, response).viewAllRelations();
       return "addEmployee.jsp";
		
	}

	private String deleteMultiple() {
		new EmployeeService(request, response).deleteMultiple();
        return "Controller?process=EmployeeProcess&action=ViewAllEmployee";
	}

	private String updateEmployee() {
		return "Controller?process=EmployeeProcess&action=viewDetails&id=" + new EmployeeService(request, response).updateEmployee();
	}

	private String updateEmployeeDetails() {
		if (new EmployeeService(request, response).viewDetailsEmployee()) {
            //return "patientDetails_1.jsp";
            return "employee_update.jsp";
        } else {
            return "viewAll.jsp";
        }
	}

	private String viewDetails() {
		new EmployeeService(request, response).viewDetailsEmployee();
		return "employee_details.jsp";
	}

	private String viewEmployee() {
		new EmployeeService(request, response).ViewAllEmployee();
		System.out.println("IN action's view all Employee");
		return "viewAllEmployee.jsp";
	}

	private String addEmployee() {

		if (new EmployeeService(request, response).addEmployee()) {
			return "Employeesaved.jsp";
		} else {
			return "EmployeenotSaved.jsp";
		}
	}

}
