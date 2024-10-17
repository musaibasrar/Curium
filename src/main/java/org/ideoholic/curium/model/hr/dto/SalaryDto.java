package org.ideoholic.curium.model.hr.dto;

import lombok.Data;

@Data
public class SalaryDto {

    private String[] staffids;
    private String month;
    private String year;
    private String dateProcess;
    private String[] idPayHeadStaffDetails;
    private String[] idProcessSalaryDetails;

}
