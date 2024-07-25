package org.ideoholic.curium.model.mess.supplier.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ChequeDetailsDto {
    private String date;
    private String bankName;
    private String[] supplierIds;
    private Map<String, String> requestParams;
}
