package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TrailBalanceDto {
    private Accountdetails accountDetails;
    private BigDecimal amount;
}
