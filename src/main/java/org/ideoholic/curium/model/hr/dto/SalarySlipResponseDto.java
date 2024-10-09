package org.ideoholic.curium.model.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SalarySlipResponseDto {

    private Processsalarydetails processSalaryDetails ;
    private Map<String, BigDecimal> earningsMap ;
    private Map<String, BigDecimal> deductionsMap  ;
    private BigDecimal totalEarnings ;
    private BigDecimal totalDeductions ;
    private String netPay;

}
