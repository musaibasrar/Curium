package org.ideoholic.curium.model.mess.supplier.dto;

import lombok.Data;

@Data
public class ChequeDto {
    private String date;
    private String supplierId;
    private String chequeNo;
    private String issueAmount;
}
