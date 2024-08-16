package org.ideoholic.curium.model.feescollection.dto;

import lombok.Data;

@Data
public class CancelledReceiptsDto {
    private String branchId;
    private String toDate;
    private String fromDate;
    private String oneDay;
    private String[] feesIds;
    private String toDateOtherFees;
    private String fromDateOtherFees;
    private String oneDayOtherFees;
}
