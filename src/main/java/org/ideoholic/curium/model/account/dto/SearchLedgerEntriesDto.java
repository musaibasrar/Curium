package org.ideoholic.curium.model.account.dto;


import lombok.Data;

@Data
public class SearchLedgerEntriesDto {
    private String accountDetails;
    private String[] accountIdName;
    private String fromDate;
    private String toDate;
    private Integer branchId;
}
