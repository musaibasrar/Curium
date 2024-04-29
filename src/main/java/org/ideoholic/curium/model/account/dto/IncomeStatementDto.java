package org.ideoholic.curium.model.account.dto;

import lombok.Data;

@Data
public class IncomeStatementDto {
    private String fromDate;
    private String toDate;
    private int branchId;
}
