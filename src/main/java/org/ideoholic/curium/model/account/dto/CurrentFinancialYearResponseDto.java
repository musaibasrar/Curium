package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CurrentFinancialYearResponseDto {
    private Date financialStartDate;
    private Date financialEndDate;
    private boolean success;
}
