package org.ideoholic.curium.model.employee.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeListDto {
    private List<Teacher> employeeList;
}
