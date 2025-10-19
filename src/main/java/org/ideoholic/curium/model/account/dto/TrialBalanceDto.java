package org.ideoholic.curium.model.account.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrialBalanceDto {
private	Accountdetails accountDetails; 
private BigDecimal amount;
}
