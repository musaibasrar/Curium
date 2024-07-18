package org.ideoholic.curium.model.mess.stockmove.dto;

import lombok.Data;

@Data
public class StockMoveDto {
    private String[] StockEntryIds;
    private String[] itemsName;
    private String[] itemsIds;
    private String[] issueQuantity;
    private String[] itemUintPrice;
    private String[] purchasePrice;
    private String custDetails;
    private String[] sgst;
    private String[] cgst;
    private String[] uom;
    private String[] batchNo;
    private String[] singleItemTotal;
    private String paymentMethodBankTransfer;
    private String paymentMethodChequeTransfer;
    private String paymentMethodCash;
    private String ackNo;
    private String transferDate;
    private String transferBankName;
    private String chequeNo;
    private String chequeDate;
    private String chequeBankName;
    private String totalCashAmount;
    private String totalBankTransferAmount;
    private String totalChequeTransferAmount;
    private String itemsGrandTotalAmountWOGST;
    private String transactionDate;
}
