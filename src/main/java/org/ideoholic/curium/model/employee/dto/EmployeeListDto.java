package org.ideoholic.curium.model.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class EmployeeListDto {
    private List<Teacher> employeeList;
}
