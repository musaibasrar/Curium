package org.ideoholic.curium.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SearchByDateResponseDto {
    private String feesDetailsBranchName;
    private String branchName;
    private String dateFrom;
    private String dateTo;
    private String dayOne;
    private Map<Receiptinfo, Parents> feesMap;
    private double sumOfFees;
    private double sumOfOnlyFee;
    private double fine;
    private double misc;
    @Builder.Default
    private boolean success = false;
}
