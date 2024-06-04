package org.ideoholic.curium.model.account.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class BalanceSheetResponseDto {
    private BigDecimal Liabilities;
    private Map liabilitiesLedgerAccount;
    private BigDecimal Reserves;
    private Map reservesLedgerAccount;
    private BigDecimal Assets;
    private Map assetsLedgerAccount;
    private boolean success;
}
