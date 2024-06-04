package org.ideoholic.curium.model.account.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class BalanceSheetResponseDto {
    private BigDecimal liabilities;
    private Map liabilitiesLedgerAccount;
    private BigDecimal reserves;
    private Map reservesLedgerAccount;
    private BigDecimal assets;
    private Map assetsLedgerAccount;
    private boolean success;
}
