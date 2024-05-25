package org.ideoholic.curium.model.account.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ViewNextVoucherResponseDto {
    private Map voucherTransactions;
    private String voucherType;
    private String fromDateSelected;
    private String toDateSelected;
    private boolean success;
}
