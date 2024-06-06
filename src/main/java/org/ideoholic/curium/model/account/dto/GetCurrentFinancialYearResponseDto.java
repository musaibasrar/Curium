package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCurrentFinancialYearResponseDto {
    private Financialaccountingyear financialaccountingyear;
    private Financialaccountingyear financialStartDate;
    private Financialaccountingyear financialEndDate;
    private boolean success;
}
