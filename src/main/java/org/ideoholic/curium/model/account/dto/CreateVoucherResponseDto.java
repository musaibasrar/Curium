package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateVoucherResponseDto {
    private List accountDetailsBalance;
    private List accountDetailsBalanceExpenses;
    private List accountDetailsBalanceBankCash;
    private List accountDetailsJournalEntry;
    private boolean success;
}
