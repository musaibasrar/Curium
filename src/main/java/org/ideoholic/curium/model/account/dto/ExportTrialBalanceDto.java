package org.ideoholic.curium.model.account.dto;


import lombok.Data;

@Data
public class ExportTrialBalanceDto {
    private String creditAllAcc;
    private String debitAllAcc;
    private String fromDate;
    private String toDate;
}
