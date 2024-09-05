package org.ideoholic.curium.model.mess.item.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.dto.*;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.action.StudentActionAdapter;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/messItemsProcess")
public class MessItemsApiActionImpl implements MessItemsApiAction {

    @Autowired
    private MessItemsService messItemsService;

    @Autowired
    private MessSuppliersService messSuppliersService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private StandardActionAdapter standardActionAdapter;

    @PostMapping("/printStockReceivedReport")
    public String printStockReceivedReport() {
        return "printstockreceivedreport";
    }

    @PostMapping("/generateStockReceivedReport")
    public ResponseEntity<StockReportResponseDto> generateStockReceivedReport(@RequestBody StockReportDto dto, @RequestHeader(value = "branchid") String branchId) {
        StockReportResponseDto result = messItemsService.generateStockReceivedReport(dto, branchId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/receiveStock")
    public ResponseEntity<ResultResponse> receiveStockReport(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messItemsService.receiveStockReport(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/printStockIssuanceReport")
    public String printStockIssuanceReport() {
        return "printstockissuancereport";
    }

    @PostMapping("/generateStockIssuanceReport")
    public ResponseEntity<IssuanceReportResponseDto> generateStockIssuanceReport(@RequestBody IssuanceReportDto dto, @RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId) {
        IssuanceReportResponseDto result = messItemsService.generateStockIssuanceReport(dto);

        // TODO: Need to fix this after migrating StudentService
        new StudentService(request, response, standardActionAdapter).viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/issuanceStock")
    public ResponseEntity<ResultResponse> issuanceStock(@RequestParam(value = "page") String page, @RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messItemsService.getIssuanceStock();

        // TODO: Need to fix this after migrating StudentService
        new StudentService(request, response, standardActionAdapter).viewAllStudentsParents(page, branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/printBatchStockAvailability")
    public ResponseEntity<ResultResponse> printBatchStockAvailability() {
        ResultResponse result = messItemsService.getBatchStock();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/batchStock")
    public ResponseEntity<ResultResponse> batchStock() {
        ResultResponse result = messItemsService.getBatchStock();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/printStockAvailability")
    public ResponseEntity<ResultResponse> printStockAvailability() {
        ResultResponse result = messItemsService.getCurrentStock();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/currentStock")
    public ResponseEntity<ResultResponse> currentStock() {
        ResultResponse result = messItemsService.getCurrentStock();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cancelPurchase")
    public ResponseEntity<PurchaseCancelDto> cancelPurchase(@RequestBody InvoiceIdsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page) {
        PurchaseCancelDto purchaseCancelDto = new PurchaseCancelDto();

       ResultResponse cancelPurchaseResult = messItemsService.cancelPurchase(dto);

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        purchaseCancelDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        ResultResponse itemDetailsResult = messItemsService.viewItemDetails(branchId);
        purchaseCancelDto.setMessStockAvailabilityList(itemDetailsResult.getResultList());

        InvoiceDetailsResponseDto invoiceDetailsResult = messItemsService.getInvoiceDetails(page, branchId);
        purchaseCancelDto.setInvoiceSuppliersMap(invoiceDetailsResult.getInvoiceSuppliersMap());
        purchaseCancelDto.setNoOfPages(invoiceDetailsResult.getNoOfPages());
        purchaseCancelDto.setCurrentPage(invoiceDetailsResult.getCurrentPage());

        return ResponseEntity.ok(purchaseCancelDto);
    }

    @PostMapping("/savePurchase")
    public ResponseEntity<PurchaseCancelDto> savePurchase(@RequestBody PurchaseDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestParam(value = "page") String page) {
        PurchaseCancelDto purchaseCancelDto = new PurchaseCancelDto();

        ResultResponse savePurchaseResult = messItemsService.savePurchase(dto, branchId, userId);
        purchaseCancelDto.setResult(savePurchaseResult.isSuccess());

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        purchaseCancelDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        ResultResponse itemDetailsResult = messItemsService.viewItemDetails(branchId);
        purchaseCancelDto.setMessStockAvailabilityList(itemDetailsResult.getResultList());

        InvoiceDetailsResponseDto invoiceDetailsResult = messItemsService.getInvoiceDetails(page, branchId);
        purchaseCancelDto.setInvoiceSuppliersMap(invoiceDetailsResult.getInvoiceSuppliersMap());
        purchaseCancelDto.setNoOfPages(invoiceDetailsResult.getNoOfPages());
        purchaseCancelDto.setCurrentPage(invoiceDetailsResult.getCurrentPage());

        return ResponseEntity.ok(purchaseCancelDto);
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
    public ResponseEntity addItems(@RequestBody ItemDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId) {
        messItemsService.addItemDetails(dto, branchId, userId);
        return viewItems(branchId);
    }

    @ResponseBody
    @GetMapping("/purchaseItems")
    public ResponseEntity<PurchaseCancelDto> purchaseItems(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page) {
        PurchaseCancelDto purchaseCancelDto = new PurchaseCancelDto();

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        purchaseCancelDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        ResultResponse itemDetailsResult = messItemsService.viewItemDetails(branchId);
        purchaseCancelDto.setMessStockAvailabilityList(itemDetailsResult.getResultList());

        InvoiceDetailsResponseDto invoiceDetailsResult = messItemsService.getInvoiceDetails(page, branchId);
        purchaseCancelDto.setInvoiceSuppliersMap(invoiceDetailsResult.getInvoiceSuppliersMap());
        purchaseCancelDto.setNoOfPages(invoiceDetailsResult.getNoOfPages());
        purchaseCancelDto.setCurrentPage(invoiceDetailsResult.getCurrentPage());

        return ResponseEntity.ok(purchaseCancelDto);
    }

    @GetMapping("/addsuppliers")
    public String addSuppliers() {
        return "addsuppliers";
    }
}
