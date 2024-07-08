package org.ideoholic.curium.model.employee.dto;

import lombok.Data;
import org.ideoholic.curium.model.department.dto.Department;

import java.util.List;

@Data
public class ViewDepartmentsResponseDto {
private List<Department> listDepartment;
}
