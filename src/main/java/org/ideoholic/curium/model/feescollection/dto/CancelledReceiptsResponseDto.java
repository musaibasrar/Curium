package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class CancelledReceiptsResponseDto {
    private String feesDetailsBranchName;
    private String branchName;
    private String dayOneCancel;
    private String dateFromCancel;
    private String dateToCancel;
    private List<Receiptinfo> feesDetailsList;
    private long sumOfFees;
    private boolean success;
}
