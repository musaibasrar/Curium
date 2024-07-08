package org.ideoholic.curium.model.employee.dto;

import lombok.Data;
import org.ideoholic.curium.model.hr.dto.Paybasic;

import java.util.List;

@Data
public class BasicPayEmployeesDto {
    private List<Paybasic> basicPay;
}
