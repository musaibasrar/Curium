package org.ideoholic.curium.model.account.dto;

import lombok.Data;

@Data
public class AccountFinancialYearDto {
    private String fromDate;
    private String toDate;
    private String active;
    private int branchId;
}
