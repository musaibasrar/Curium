package org.ideoholic.curium.model.account.dto;


import lombok.Data;

@Data
public class SearchLedgerEntriesDto {
    private String accountDetails;
    private String fromDate;
    private String toDate;
}
