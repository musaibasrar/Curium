package org.ideoholic.curium.model.hr.dto;

import lombok.Data;

@Data
public class SaveAdvanceSalaryDto {

    private String amount;
    private String deductionPerMonth;
    private String deductionMonth;
    private String deductionYear;
    private String staffId;
    private String year;
    private String month;
    private String salaryForDay;
    private String dateAdvance;



}
