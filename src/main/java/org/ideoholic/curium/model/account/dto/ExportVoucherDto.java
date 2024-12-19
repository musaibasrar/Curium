package org.ideoholic.curium.model.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ExportVoucherDto {
    private String fromDate;
    private String toDate;
    private String nextVoucher;
}
