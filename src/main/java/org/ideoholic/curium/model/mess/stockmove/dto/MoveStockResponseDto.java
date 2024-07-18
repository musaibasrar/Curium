package org.ideoholic.curium.model.mess.stockmove.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MoveStockResponseDto {
    private List<Bill> billList;
    private String billDetailsTransactionDate;
    private String billDetailsStudentName;
    private String billDetailsClassStudying;
    private String billDetailsFatherName;
    private String billDetailsTotalTotal;
    private BigDecimal billGrandTotal;
    private int billNo;
    private String billDetails;
    private String billDetailsCustomerName;
    public boolean itemsIssued;
    private boolean success;
}
