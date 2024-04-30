package org.ideoholic.curium.model.account.dto;


import lombok.Data;

@Data
public class PrintSearchJournalEntriesDto {
    private String accountDetails;
    private String fromDate;
    private String toDate;
    private Integer branchId;
}
