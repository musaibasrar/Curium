package org.ideoholic.curium.model.mess.item.dto;

import lombok.Data;

@Data
public class IssuanceReportDto {
    private String fromDate;
    private String toDate;
    private String issueTo;
    private String purpose;
    private String item;
}