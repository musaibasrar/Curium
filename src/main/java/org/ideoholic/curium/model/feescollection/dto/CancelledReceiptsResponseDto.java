package org.ideoholic.curium.model.feescollection.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;
import java.util.Map;

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
    private List<Otherreceiptinfo> otherfeesDetailsList;
    private Map<Parents,Otherreceiptinfo> feesMap;
    private long sumOfFees;
    @Builder.Default
    private boolean success = false;
}
