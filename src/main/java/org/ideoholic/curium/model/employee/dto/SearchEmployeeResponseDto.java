package org.ideoholic.curium.model.employee.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchEmployeeResponseDto {
    private List<Teacher> searchedEmployeeList;

}
