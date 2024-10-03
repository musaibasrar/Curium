package org.ideoholic.curium.model.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SalaryResponseDto {
     private List<Payadvancesalary> payAdvanceSalary;
     private boolean success;
     private List<Processsalarydetails> processSalaryDetailsList;
}
