package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ExportVoucherDto {
    private String fromDate;
    private String toDate;
    private Integer branchId;
    private int voucherType;
    private String nextVoucher;
    private boolean success;
}
