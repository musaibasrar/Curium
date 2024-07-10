package org.ideoholic.curium.model.mess.item.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.util.StockIssuance;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class IssuanceReportResponseDto {
    private String issuedToSelected;
    private String itemSelected;
    private List<StockIssuance> stockIssuanceList;
    private String transactionFromDateSelected;
    private String transactionToDateSelected;
    private boolean success;
}
