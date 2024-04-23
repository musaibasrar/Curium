package org.ideoholic.curium.model.account.dto;

import lombok.Data;

@Data
public class CancelVoucherDto {
    private String[] receiptIds;
    private String voucher;
}
