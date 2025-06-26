package org.ideoholic.curium.model.employee.dto;

import lombok.Data;

import java.util.List;

import org.ideoholic.curium.model.user.dto.Login;

@Data
public class EmployeeDetailsResponseDto {
    private Teacher employee;
    private Login employeeLogin;
    private boolean success;
    private List<String> classTeacher;
    private List<String> classesTeaching;
}
