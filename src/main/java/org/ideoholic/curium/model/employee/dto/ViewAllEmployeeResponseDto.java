package org.ideoholic.curium.model.employee.dto;

import lombok.Data;

import java.util.List;

import org.ideoholic.curium.model.feescategory.dto.StudentListResponseDto;

@Data
public class ViewAllEmployeeResponseDto {
    private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private boolean success=false;
	
}
