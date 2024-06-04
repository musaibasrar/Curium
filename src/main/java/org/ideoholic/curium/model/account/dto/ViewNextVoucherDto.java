package org.ideoholic.curium.model.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewNextVoucherDto {
    private String fromDate;
    private String toDate;
    private String nextVoucher;
    private int voucherType;
    private Integer branchId;
}
