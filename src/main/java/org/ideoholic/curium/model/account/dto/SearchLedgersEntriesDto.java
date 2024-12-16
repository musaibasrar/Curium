package org.ideoholic.curium.model.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SearchLedgersEntriesDto {
    private Map<VoucherEntrytransactions,String> ledgerTransaction;
    private String ledgerName;
    private String accountId;
    private String fromDate;
    private String toDate;
    private String voucherType;
    private List<Accountdetails> accountDetails;
}
