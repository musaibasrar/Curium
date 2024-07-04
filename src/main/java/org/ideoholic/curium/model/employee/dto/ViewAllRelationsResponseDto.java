package org.ideoholic.curium.model.employee.dto;

import lombok.Data;
import org.ideoholic.curium.model.department.dto.Department;
import org.ideoholic.curium.model.position.dto.Position;

import java.util.List;

@Data
public class ViewAllRelationsResponseDto {
private List<Department> listDepartment;
private List<Position> listPosition;
}
