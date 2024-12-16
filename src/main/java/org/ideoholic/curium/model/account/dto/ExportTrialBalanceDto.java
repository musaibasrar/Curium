package org.ideoholic.curium.model.account.dto;


import lombok.Data;

import java.util.Map;

@Data
public class ExportTrialBalanceDto {
    private String creditAllAcc;
    private String debitAllAcc;
    private String fromDate;
    private String toDate;
    private Map<String, TrailBalanceDto> trailBalanceDto;
}
