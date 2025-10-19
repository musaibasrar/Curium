package org.ideoholic.curium.model.department.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class DepartmentResponseDto {
private List<Department> departmentList;
private boolean success;
}
