package org.ideoholic.curium.model.mess.item.dto;

import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;

import java.util.List;

@Data
@Builder
public class StockReportResponseDto {
    private String supplierSelected;
    private String itemSelected;
    private List<MessStockEntry> messStockEntryList;
    private boolean success;
    private String transactionFromDateSelected;
    private String transactionToDateSelected;
}
