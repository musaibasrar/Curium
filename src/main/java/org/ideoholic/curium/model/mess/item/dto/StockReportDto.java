package org.ideoholic.curium.model.mess.item.dto;

import lombok.Data;

@Data
public class StockReportDto {
    private String fromDate;
    private String toDate;
    private String supplierId;
    private String item;
}
