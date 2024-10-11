package org.ideoholic.curium.model.mess.stockmove.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.mess.item.service.MessItemsService;
import org.ideoholic.curium.model.mess.stockmove.dto.*;
import org.ideoholic.curium.model.mess.stockmove.service.MessStockMoveService;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.std.service.StandardService;
import org.ideoholic.curium.model.student.action.StudentActionAdapter;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/messItemsMoveProcess")
public class MessStockMoveApiActionImpl implements MessStockMoveApiAction {

    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    private StandardActionAdapter standardActionAdapter;
    @Autowired
    private MessStockMoveService messStockMoveService;
    @Autowired
    private MessItemsService messItemsService;
    @Autowired
    private StandardService standardService;

    @PostMapping("/cancelStockMove")
    public ResponseEntity<StockMoveCancelDto> cancelStockMove(@RequestBody StockMoveIdsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestParam(value = "page") String page) {
        StockMoveCancelDto stockMoveCancelDto = new StockMoveCancelDto();

        ResultResponse stockMoveResult = messStockMoveService.cancelStockMove(dto, branchId, userId);
        stockMoveCancelDto.setResult(stockMoveResult.isSuccess());

        ResultResponse stockToIssueResult = messItemsService.getCurrentStockToIssue();
        stockMoveCancelDto.setMessStockAvailability(stockMoveResult.getResultList());
        /*
         * Batch stock issue new MessStockMoveService(request,
         * response).viewStockEntryDetails();
         */
        StockMoveResponseDto stockMoveDetailsResult = messStockMoveService.viewStockMoveDetails(page, branchId);
        stockMoveCancelDto.setCurrentPage(stockMoveDetailsResult.getCurrentPage());
        stockMoveCancelDto.setNoOfPages(stockMoveDetailsResult.getNoOfPages());
        stockMoveCancelDto.setMessStockMoveList(stockMoveDetailsResult.getMessStockMoveList());

        return ResponseEntity.ok(stockMoveCancelDto);
    }

    @RequestMapping(value = "/issueItems", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<StockMoveCancelDto> issueItems(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page) {
        StockMoveCancelDto stockMoveCancelDto = new StockMoveCancelDto();

        ResultResponse stockToIssueResult = messItemsService.getCurrentStockToIssue();
        stockMoveCancelDto.setMessStockAvailability(stockToIssueResult.getResultList());
        /*
         * Batch stock issue new MessStockMoveService(request,
         * response).viewStockEntryDetails();
         */

        //Batch stock issue
        ResultResponse entryDetailsResult = messStockMoveService.viewStockEntryDetails(branchId);
        stockMoveCancelDto.setMessStockItemDetailsList(entryDetailsResult.getResultList());

        //Get Customers
        // TODO: Need to fix this after migrating StudentService
        new StudentService(request, response, standardActionAdapter).viewStudentsParentsPerBranch(branchId);



        StockMoveResponseDto stockMoveDetailsResult = messStockMoveService.viewStockMoveDetails(page, branchId);
        stockMoveCancelDto.setCurrentPage(stockMoveDetailsResult.getCurrentPage());
        stockMoveCancelDto.setNoOfPages(stockMoveDetailsResult.getNoOfPages());
        stockMoveCancelDto.setMessStockMoveList(stockMoveDetailsResult.getMessStockMoveList());

        return ResponseEntity.ok(stockMoveCancelDto);
    }

    @PostMapping("/saveStockMove")
    public ResponseEntity<StockMoveSaveDto> saveStockMove(@RequestBody StockMoveDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestHeader(value = "username") String userName,  @RequestParam(value = "page") String page) {
        StockMoveSaveDto stockMoveSaveDto = new StockMoveSaveDto();

        MoveStockResponseDto stockMoveResult = messStockMoveService.saveStockMove(dto, branchId, userId, userName);
        stockMoveSaveDto.setBillList(stockMoveResult.getBillList());
        stockMoveSaveDto.setBillDetailsTransactionDate(stockMoveResult.getBillDetailsTransactionDate());
        stockMoveSaveDto.setBillDetailsStudentName(stockMoveResult.getBillDetailsStudentName());
        stockMoveSaveDto.setBillDetailsClassStudying(stockMoveResult.getBillDetailsClassStudying());
        stockMoveSaveDto.setBillDetailsFatherName(stockMoveResult.getBillDetailsFatherName());
        stockMoveSaveDto.setBillDetailsTotalTotal(stockMoveResult.getBillDetailsTotalTotal());
        stockMoveSaveDto.setBillGrandTotal(stockMoveResult.getBillGrandTotal());
        stockMoveSaveDto.setBillNo(stockMoveResult.getBillNo());
        stockMoveSaveDto.setBillDetails(stockMoveResult.getBillDetails());
        stockMoveSaveDto.setBillDetailsCustomerName(stockMoveResult.getBillDetailsCustomerName());
        stockMoveSaveDto.setItemsIssued(stockMoveResult.isItemsIssued());

        ResultResponse stockToIssueResult = messItemsService.getCurrentStockToIssue();
        stockMoveSaveDto.setMessStockAvailability(stockToIssueResult.getResultList());
        /*
         * Batch stock issue new MessStockMoveService(request,
         * response).viewStockEntryDetails();
         */
        //Batch stock issue
        ResultResponse entryDetailsResult = messStockMoveService.viewStockEntryDetails(branchId);
        stockMoveSaveDto.setMessStockItemDetailsList(entryDetailsResult.getResultList());

        StockMoveResponseDto stockMoveDetailsResult = messStockMoveService.viewStockMoveDetails(page, branchId);
        stockMoveSaveDto.setCurrentPage(stockMoveDetailsResult.getCurrentPage());
        stockMoveSaveDto.setNoOfPages(stockMoveDetailsResult.getNoOfPages());
        stockMoveSaveDto.setMessStockMoveList(stockMoveDetailsResult.getMessStockMoveList());
        //Get Student
        // TODO: Need to fix this after migrating StudentService
        new StudentService(request, response, standardActionAdapter).viewAllStudentsParents(page, branchId);

        return ResponseEntity.ok(stockMoveSaveDto);
        //return "issuestock";

    }

    @GetMapping("/billsReport")
    public ResponseEntity<StockMoveResponseDto> billsReport(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page) {

        StockMoveResponseDto result = messStockMoveService.viewStockMoveDetails(branchId, page);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/dueReport")
    public ResponseEntity<ResultResponse> dueReport(@RequestBody ClassSearchDto dto, @RequestHeader(value = "branchid") String branchId) {

        ResultResponse result = messStockMoveService.viewStockDueDetails(dto, branchId);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/getDueReport")
    public ResponseEntity<ResultResponse> getDueReport(@RequestHeader(value = "branchid") String branchId) {

        ResultResponse result = standardService.viewClasses(branchId);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/getCustomerLastPrice")
    public ResponseEntity getCustomerLastPrice(@RequestHeader(value = "customerName") String customerName, @RequestHeader(value = "customerName") String strCustDetails, @RequestHeader(value = "itemid") String itemId, @RequestHeader(value = "branchid") String branchId) {

        try {
            messStockMoveService.getCustomerLastPrice(customerName, strCustDetails, itemId, branchId);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

}
