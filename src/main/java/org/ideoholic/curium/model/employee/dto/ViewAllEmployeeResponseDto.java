package org.ideoholic.curium.model.employee.dto;

import lombok.Data;

import java.util.List;

@Data
public class ViewAllEmployeeResponseDto {
    private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    private boolean success=false;
}
