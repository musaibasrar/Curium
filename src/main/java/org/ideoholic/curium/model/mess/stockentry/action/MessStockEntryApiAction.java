package org.ideoholic.curium.model.mess.stockentry.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.dto.*;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntryResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

public interface MessStockEntryApiAction {

    ResponseEntity<MessStockEntryResponseDto> mrvDetails(@RequestParam(value = "invoicedetailsid") String invoiceDetailsId, @RequestParam(value = "supplierreferenceno") String supplierRefNo, @RequestParam(value = "invoicetotal") String invoiceTotal, @RequestParam(value = "suppliername") String supplierName, @RequestParam(value = "entrydate") String invoiceDate, @RequestHeader(value = "branchid") String branchId);

    String savePurchase();

    ResponseEntity<ResultResponse> deleteItems(@RequestBody MessIdsDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> updateItems(@RequestBody ItemsDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> viewItems(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ItemDetailsResponseDto> addItems(@RequestBody ItemDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId);

    ResponseEntity<PurchaseCancelDto> purchaseItems(@RequestHeader(value = "branchid") String branchId);

    String addSuppliers();

}
