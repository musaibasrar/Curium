package org.ideoholic.curium.model.employee.dto;

import lombok.Data;

@Data
public class ViewAllEmployeeResponseDto {
    private String employeeList;
    private String employeeListProcessSalary;
    private boolean success;
}
