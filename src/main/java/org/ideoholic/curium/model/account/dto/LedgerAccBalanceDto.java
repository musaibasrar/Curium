package org.ideoholic.curium.model.account.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class LedgerAccBalanceDto {
    private Accountdetails accountdetails;
    private BigDecimal balance;
}