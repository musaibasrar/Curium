package org.ideoholic.curium.model.account.dto;

import lombok.Data;

@Data
public class AccountJournalDto {
    private String draccountNameJournal;
    private String craccountNameJournal;
    private String journalVoucher;
    private String drAmountJournal;
    private String crAmountJournal;
    private String journalDate;
    private String journalNarration;
}
