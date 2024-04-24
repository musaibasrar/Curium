package org.ideoholic.curium.model.account.dto;

import lombok.Data;

@Data
public class AccountPaymentDto {
    private String draccountName;
    private String craccountName;
    private String paymentVoucher;
    private String drAmountPayment;
    private String crAmountPayment;
    private String paymentDate;
    private String paymentNarration;
}
