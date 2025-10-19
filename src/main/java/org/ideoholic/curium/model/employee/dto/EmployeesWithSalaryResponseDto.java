package org.ideoholic.curium.model.employee.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class EmployeesWithSalaryResponseDto {
    private List<Teacher> employeeList;
    private List<Teacher> employeeListProcessSalary;
    @Builder.Default
    private boolean success = false;
}