package org.ideoholic.curium.model.mess.stockmove.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.stockmove.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface MessStockMoveApiAction {

    ResponseEntity<StockMoveCancelDto> cancelStockMove(@RequestBody StockMoveIdsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestParam(value = "page") String page);

    ResponseEntity<StockMoveCancelDto> issueItems(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page);

    ResponseEntity<StockMoveSaveDto> saveStockMove(@RequestBody StockMoveDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestHeader(value = "username") String userName,  @RequestParam(value = "page") String page);

    ResponseEntity<StockMoveResponseDto> billsReport(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page);

    ResponseEntity<ResultResponse> dueReport(@RequestBody ClassSearchDto dto, @RequestHeader(value = "branchid") String branchId);

    ResponseEntity<ResultResponse> getDueReport(@RequestHeader(value = "branchid") String branchId);

    ResponseEntity getCustomerLastPrice(@RequestHeader(value = "customerName") String customerName, @RequestHeader(value = "customerName") String strCustDetails, @RequestHeader(value = "itemid") String itemId, @RequestHeader(value = "branchid") String branchId);

}
