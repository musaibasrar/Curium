package org.ideoholic.curium.model.department.dto;

import lombok.Data;

import java.util.List;

@Data
public class ViewDepartmentResponseDto {
private List<Department> departmentList;
}
