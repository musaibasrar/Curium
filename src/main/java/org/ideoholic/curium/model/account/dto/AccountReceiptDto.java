package org.ideoholic.curium.model.account.dto;

import lombok.Data;


@Data
public class AccountReceiptDto {
    private String draccountName;
    private String craccountName;
    private String receiptVoucher;
    private String drAmount;
    private String crAmount;
    private String receiptDate;
    private String receiptNarration;
}
