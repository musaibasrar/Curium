package org.ideoholic.curium.model.mess.stockmove.dto;

import lombok.Data;

import java.util.Map;

@Data
public class StockMoveOldDto {
    private String[] stockEntryIds;
    private String transactionDate;
    private String purpose;
    private String issuedTo;
    private Map<String, String> requestParams;
}
