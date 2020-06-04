package org.ideoholic.department.service;

public interface DepartmentService {

	String addDepartment(String branchId, String department1);

	String viewDepartment(String branchId);

	String deleteMultiple(String[] departmentIds);

}
