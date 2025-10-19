package org.ideoholic.curium.model.employee.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmployeesWithSalaryResponseDto {
    private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private boolean success=false;
	
}
