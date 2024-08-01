package org.ideoholic.curium.model.mess.item.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.dto.IssuanceReportResponseDto;
import org.ideoholic.curium.model.mess.item.dto.PurchaseCancelDto;
import org.ideoholic.curium.model.mess.item.dto.StockReportResponseDto;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/messItemsProcess")
public class MessItemsApiAction {

    public String printStockReceivedReport;

    public ResponseEntity<StockReportResponseDto> generateStockReceivedReport;

    public ResponseEntity<ResultResponse> receiveStockReport;

    public String printStockIssuanceReport;


    public ResponseEntity<IssuanceReportResponseDto> generateStockIssuanceReport;

    public ResponseEntity<ResultResponse> issuanceStock;

    public ResponseEntity<ResultResponse> printBatchStockAvailability;

    public ResponseEntity<ResultResponse> batchStock;

    public ResponseEntity<ResultResponse> printStockAvailability;

    public ResponseEntity<ResultResponse> currentStock;

    public ResponseEntity<PurchaseCancelDto> cancelPurchase;

    public ResponseEntity<PurchaseCancelDto> savePurchase;

    public ResponseEntity<ResultResponse> deleteItems;

    public ResponseEntity<ResultResponse> updateItems;

    public ResponseEntity<ResultResponse> viewItems;

    public ResponseEntity addItems;

    public ResponseEntity<PurchaseCancelDto> purchaseItems;

    public String addSuppliers;
}
