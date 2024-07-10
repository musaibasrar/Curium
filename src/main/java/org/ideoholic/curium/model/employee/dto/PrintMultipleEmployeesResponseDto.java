package org.ideoholic.curium.model.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class PrintMultipleEmployeesResponseDto {
    private int initialValue;
    private int endValue;
    private boolean success;
    private Map<String, String> resultParams = new HashMap<>();
}
