package org.ideoholic.curium.model.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SearchJournalEntriesResponseDto {
    @Builder.Default
    private boolean success = false;
    private Map<VoucherEntrytransactions, String> ledgerTransaction;
    private String ledgerName;
    private String message;
    private String fromDate;
    private String toDate;
    private BigDecimal openingBalance;
    private BigDecimal closingBalance;
}
