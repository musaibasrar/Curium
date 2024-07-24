package org.ideoholic.curium.model.mess.stockmove.dto;

import lombok.Data;

import java.util.Map;

@Data
public class StockMoveIdsDto {
    private String[] stockMoveIds;
    private Map<String, String> requestParams;
}
