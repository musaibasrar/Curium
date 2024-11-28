package org.ideoholic.curium.model.account.dto;


import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayBookDto {
    private String fromDate;
    private String toDate;
    private Integer branchId;
    private Map voucherEntryTransactions;
    private boolean success;
}
