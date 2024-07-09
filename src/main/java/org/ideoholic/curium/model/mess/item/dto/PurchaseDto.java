package org.ideoholic.curium.model.mess.item.dto;

import lombok.Data;

@Data
public class PurchaseDto {
    private String itemsTotal;
    private String[] itemIds;
    private String[] itemsName;
    private String[] itemsQuantity;
    private String[] salesPrice;
    private String[] batchNo;
    private String[] lineTotal;
    private String[] purchasePrice;
    private String[] sGst;
    private String[] cGst;
    private String supplierId;
    private String invoiceDate;
    private String supplierReferenceNo;
    private String itemEntryDate;
    private String transportationCharges;
}
