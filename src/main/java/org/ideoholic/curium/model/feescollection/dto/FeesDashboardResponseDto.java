package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.std.dto.Classsec;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class FeesDashboardResponseDto {
    private Long totalFeesAmount;
    private Long totalPaidAmount;
    private Long totalDueAmount;
    private String branchIdName;
    private String branchName;
    private Long sumOfFeesDaily;
    private Long sumOfFeesMonthly;
    private String currentMonth;
    private List<Classsec> classsecList;

    @Builder.Default
    private boolean success = false;
}