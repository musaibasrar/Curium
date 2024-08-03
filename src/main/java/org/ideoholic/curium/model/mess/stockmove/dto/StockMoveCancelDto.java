package org.ideoholic.curium.model.mess.stockmove.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StockMoveCancelDto {
    private boolean result;
    private List<MessStockAvailability> messStockAvailability;
    private int currentPage;
    private int noOfPages;
    private List<Bill> messStockMoveList;
    private List<MessStockItemDetails> messStockItemDetailsList;
}
