package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateVoucherResponseDto {
    private List<Accountdetailsbalance> accountDetailsBalance;
    private List<Accountdetailsbalance> accountDetailsBalanceExpenses;
    private List<Accountdetailsbalance> accountDetailsBalanceBankCash;
    private List<Accountdetailsbalance> accountDetailsJournalEntry;
    private boolean success;
}
