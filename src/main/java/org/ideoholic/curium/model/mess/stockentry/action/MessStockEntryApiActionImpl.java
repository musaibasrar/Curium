package org.ideoholic.curium.model.mess.stockentry.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.dto.*;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntryResponseDto;
import org.ideoholic.curium.model.mess.stockentry.service.MessStockEntryService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/stockEntry")
public class MessStockEntryApiActionImpl implements MessStockEntryApiAction {

    @Autowired
    private MessItemsService messItemsService;
    @Autowired
    private MessStockEntryService messStockEntryService;
    @Autowired
    private MessSuppliersService messSuppliersService;


    @GetMapping("/mrvDetails")
    public ResponseEntity<MessStockEntryResponseDto> mrvDetails(@RequestParam(value = "invoicedetailsid") String invoiceDetailsId, @RequestParam(value = "supplierreferenceno") String supplierRefNo, @RequestParam(value = "invoicetotal") String invoiceTotal, @RequestParam(value = "suppliername") String supplierName, @RequestParam(value = "entrydate") String invoiceDate, @RequestHeader(value = "branchid") String branchId) {
        try {
            messStockEntryService.getMRVDetails(invoiceDetailsId, supplierRefNo, invoiceTotal, supplierName, invoiceDate, branchId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/savePurchase")
    public String savePurchase() {

        return "purchase";
    }

    @PostMapping("/deleteItems")
    public ResponseEntity<ResultResponse> deleteItems(@RequestBody MessIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        messItemsService.deleteMultipleItems(dto);
        return viewItems(branchId);
    }

    @PostMapping("/updateItems")
    public ResponseEntity<ResultResponse> updateItems(@RequestBody ItemsDto dto, @RequestHeader(value = "branchid") String branchId) {
        messItemsService.updateItems(dto);
        return viewItems(branchId);
    }

    @GetMapping("/viewItems")
    public ResponseEntity<ResultResponse> viewItems(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messItemsService.viewItemDetails(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addItems")
    public ResponseEntity<ItemDetailsResponseDto> addItems(@RequestBody ItemDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId) {
        ItemDetailsResponseDto responseDto = messItemsService.addItemDetails(dto, branchId, userId);
        ResultResponse result = viewItems(branchId).getBody();
        responseDto.setItemName(result.getMessage());
        responseDto.setItemSave(responseDto.isItemSave());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/purchaseItems")
    public ResponseEntity<PurchaseCancelDto> purchaseItems(@RequestHeader(value = "branchid") String branchId) {
        PurchaseCancelDto purchaseCancelDto = new PurchaseCancelDto();

        ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
        purchaseCancelDto.setMessSuppliersList(result.getResultList());

        ResultResponse results = messItemsService.viewItemDetails(branchId);
        purchaseCancelDto.setMessStockAvailabilityList(results.getResultList());
        return ResponseEntity.ok(purchaseCancelDto);
    }

    @RequestMapping(value = "/addSuppliers", method = { RequestMethod.GET, RequestMethod.POST })
    public String addSuppliers() {
        return "addsuppliers";
    }

}
