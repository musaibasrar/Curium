package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class TrialBalanceResponseDto {
    private Map accountBalanceMap;
    private BigDecimal creditAllAcc;
    private BigDecimal debitAllAcc;
    private String fromDate;
    private String toDate;
    private boolean success;
}
