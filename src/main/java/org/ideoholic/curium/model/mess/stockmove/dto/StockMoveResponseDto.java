package org.ideoholic.curium.model.mess.stockmove.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StockMoveResponseDto {
    private int currentPage;
    private int noOfPages;
    List<Bill> messStockMoveList;
    private boolean success;
}