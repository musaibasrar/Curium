package org.ideoholic.curium.model.mess.item.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PurchaseDto {
    private String itemsTotal;
    private String[] itemIds;
    private String[] itemsName;
    private String[] uom;
    private String[] itemsQuantity;
    private String[] salesPrice;
    private String[] batchNo;
    private String[] lineTotal;
    private String[] purchasePrice;
    private String[] stateGst;
    private String[] centerGst;
    private String supplierId;
    private String invoiceDate;
    private String supplierReferenceNo;
    private String itemEntryDate;
    private String transportationCharges;
    private String purchaseDate;
    private String externalId;
    private String  supplier;
	private String entryDate;
	private String totalItem;
	private String totalQuantityOrdered;
}
