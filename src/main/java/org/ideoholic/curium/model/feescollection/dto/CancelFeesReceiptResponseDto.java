package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class CancelFeesReceiptResponseDto {
    private String feesDetailsBranchName;
    private String branchName;
    private long sumOfFees;

    @Builder.Default
    private boolean success = false;
}