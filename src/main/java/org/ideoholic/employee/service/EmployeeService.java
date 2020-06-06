package org.ideoholic.employee.service;

import org.ideoholic.employee.dto.AddEmplParameterDto;
import org.ideoholic.user.service.UserService;

public interface EmployeeService {

	String addEmployee(String branchId, AddEmplParameterDto addParam, UserService userService);

	String ViewAllEmployee(String branchId);

	String viewDetailsEmployee(long id);

	String updateEmployee(String id1, String leavingdate, AddEmplParameterDto addParam);

	String deleteMultiple(String[] employeeIds);

	String searchEmployee(String staffName, String staffDepartment, String branchId, EmployeeService employeeService);

	

}
