package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SearchSingleLedgerEntriesResponseDto {
    private Map ledgerTransaction;
    private String ledgerName;
    private String accountId;
    private String fromDate;
    private String toDate;
    private boolean success;
}
