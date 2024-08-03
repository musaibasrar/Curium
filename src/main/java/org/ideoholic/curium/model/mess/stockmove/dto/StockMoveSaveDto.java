package org.ideoholic.curium.model.mess.stockmove.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StockMoveSaveDto {
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
    private List<MessStockAvailability> messStockAvailability;
    private List<Bill> messStockMoveList;
    private List<MessStockItemDetails> messStockItemDetailsList;
    private int currentPage;
    private int noOfPages;
}
