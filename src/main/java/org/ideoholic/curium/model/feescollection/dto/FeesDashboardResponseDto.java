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
public class FeesDashboardResponseDto {
    private Double totalFeesAmount;
    private Double totalPaidAmount;
    private Double totalDueAmount;
    private String branchIdName;
    private String branchName;
    private Double sumOfFeesDaily;
    private Double sumOfFeesMonthly;
    private String currentMonth;
    @Builder.Default
    private boolean success = false;
}
