package org.ideoholic.curium.model.account.dto;

import lombok.Data;

@Data
public class VoucherPrintDto {
    private String fromDate;
    private String toDate;
    private String nextVoucher;
    private int voucherType;
    private Integer branchId;
}
