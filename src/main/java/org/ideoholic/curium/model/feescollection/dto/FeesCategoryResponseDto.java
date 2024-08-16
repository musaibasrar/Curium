package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class FeesCategoryResponseDto {
    private String feesDetailsBranchName;
    private String branchName;
    private String dayOne;
    private String dateFrom;
    private String dateTo;
    private Map<String, Long> feeCategoryCollectionMapReport;
    private long feesByCash;
    private long feesByBank;
    private long feesByCashOtherFees;
    private long feesByBankOtherFees;
    private boolean success;

}
