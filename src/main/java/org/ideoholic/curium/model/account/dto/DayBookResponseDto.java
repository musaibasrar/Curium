package org.ideoholic.curium.model.account.dto;


import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DayBookResponseDto {
    private Map voucherEntryTransactions;
    private boolean success;
}
