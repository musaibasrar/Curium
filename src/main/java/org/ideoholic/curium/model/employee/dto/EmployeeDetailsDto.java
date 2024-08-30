package org.ideoholic.curium.model.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.position.dto.Position;
import org.ideoholic.curium.model.user.dto.Login;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDto {
    private Teacher employee;
    private Login employeeLogin;
    private List<Department> listDepartment;
    private List<Position> listPosition;
    private String message;
}
