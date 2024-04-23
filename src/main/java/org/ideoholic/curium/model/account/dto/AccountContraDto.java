package org.ideoholic.curium.model.account.dto;

import lombok.Data;

@Data
public class AccountContraDto {
    private String draccountName;
    private String craccountName;
    private String contraVoucher;
    private String drAmountContra;
    private String crAmountContra;
    private String contraDate;
    private String contraNarration;
}