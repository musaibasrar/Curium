package org.ideoholic.curium.model.employee.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/EmployeeProcess")
public class EmployeeAction {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	 EmployeeActionAdapter employeeActionAdapter;

	@PostMapping("/searchEmployee")
	public String searchEmployee() {
		new EmployeeService(request, response).searchEmployee();
		return "";
	}

	@GetMapping("/addEmployeePage")
	public String addEmployeePage() {
		employeeActionAdapter.viewAllRelations();
		return "addEmployee";
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		employeeActionAdapter.deleteMultiple();
		return viewEmployee();
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String updateEmployee(MultipartHttpServletRequest request,
			@RequestParam("fileToUpload") MultipartFile[] uploadedFiles) {
		request.setAttribute("id", employeeActionAdapter.updateEmployee(uploadedFiles));
		return viewDetails();
	}

	@PostMapping("/updateEmployeeDetails")
	public String updateEmployeeDetails() {
		if (employeeActionAdapter.viewDetailsEmployee()) {
			employeeActionAdapter.viewAllRelations();
			return "employee_update";
		} else {
			return "viewAll";
		}
	}

	@GetMapping("/ViewDetails")
	public String viewDetails() {
		new EmployeeService(request, response).viewDetailsEmployee();
		return "employee_details";
	}

	@RequestMapping(value = "/ViewAllEmployee", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewEmployee() {
		employeeActionAdapter.ViewAllEmployee();
		return "viewAllEmployee";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String addEmployee(MultipartHttpServletRequest request,
			@RequestParam("fileToUpload") MultipartFile[] uploadedFiles) {
		if (employeeActionAdapter.addEmployee(uploadedFiles)) {
			return "Employeesaved";
		} else {
			return "EmployeenotSaved";
		}
	}

}
