package org.ideoholic.curium.model.mess.item.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.dto.*;
import org.springframework.http.ResponseEntity;

public interface MessItemsApiAction {

    String printStockReceivedReport();

    ResponseEntity<StockReportResponseDto> generateStockReceivedReport(StockReportDto dto, String branchId);

    ResponseEntity<ResultResponse> receiveStockReport(String branchId);

    String printStockIssuanceReport();

    ResponseEntity<IssuanceReportResponseDto> generateStockIssuanceReport(IssuanceReportDto dto);

    ResponseEntity<ResultResponse> issuanceStock();

    ResponseEntity<ResultResponse> printBatchStockAvailability();

    ResponseEntity<ResultResponse> batchStock();

    ResponseEntity<ResultResponse> printStockAvailability();

    ResponseEntity<ResultResponse> currentStock();

    ResponseEntity<PurchaseCancelDto> cancelPurchase(InvoiceIdsDto dto, String branchId, String page);

    ResponseEntity<PurchaseCancelDto> savePurchase(PurchaseDto dto, String branchId, String userId, String page);

    ResponseEntity<ResultResponse> deleteItems(MessIdsDto dto, String branchId);

    ResponseEntity<ResultResponse> updateItems(ItemsDto dto, String branchId);

    ResponseEntity<ResultResponse> viewItems(String branchId);

    ResponseEntity addItems(ItemDetailsDto dto, String branchId, String userId);

    ResponseEntity<PurchaseCancelDto> purchaseItems(String branchId, String page);

    String addSuppliers();
}