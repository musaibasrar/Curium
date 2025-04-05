package org.ideoholic.curium.model.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class IncomeStatementResponseDto {
    @Builder.Default
    private boolean success = false;
    private BigDecimal income;
    private List<LedgerAccBalanceDto> incomeLedgersAccount;
    private BigDecimal expenses;
    private List<LedgerAccBalanceDto> expenseLedgersAccount;
    private String incomeTotalLabel;
    private String expenseTotalLabel;
    private BigDecimal incomeTotal;
    private BigDecimal expenseTotal;
    private String fromDate;
    private String toDate ;
    private String profitLabel;
    private BigDecimal totalProfit;
    private String lossLabel;
    private BigDecimal totalLoss;
}