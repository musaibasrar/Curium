package org.ideoholic.curium.model.mess.supplier.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.account.dto.PrintSearchJournalEntriesDto;
import org.ideoholic.curium.model.account.dto.SearchLedgerEntriesDto;
import org.ideoholic.curium.model.account.dto.SearchLedgerEntriesResponseDto;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.model.mess.item.dto.MessIdsDto;
import org.ideoholic.curium.model.mess.supplier.dto.*;
import org.ideoholic.curium.model.mess.supplier.service.MessSuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping({ "/api/v1/messSuppliersProcess", "/api/v1/supplierBalance" })
public class MessSuppliersApiAction {
    
    @Autowired
    private MessSuppliersService messSuppliersService;

    //TODO:Request to be removed after the migration of AccountService.
    @Autowired
    private HttpServletRequest request;



    @PostMapping("/printSearchSupplierPaymentDetails")
    public ResponseEntity printSearchSupplierPaymentDetails(@RequestBody PrintSearchJournalEntriesDto dto) {
        //TODO: Need to fix this after annotating AccountService with Service
        printSearchJournalEntries(dto);

        return ResponseEntity.ok().build();
    }
    private ResultResponse printSearchJournalEntries(PrintSearchJournalEntriesDto dto){
        AccountService accountService = new AccountService(request, null);

        ResultResponse resultResponse = accountService.printSearchJournalEntries(dto);
        return resultResponse;
    }

    @PostMapping("/searchSupplierPaymentDetails")
    public ResponseEntity<ResultResponse> searchSupplierPaymentDetails(@RequestHeader(value = "branchid") String branchId, @RequestBody SearchLedgerEntriesDto dto) {
        // TODO: Need to fix this after annotating AccountService with Service.
        searchJournalEntries(dto);

        ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
        return ResponseEntity.ok(result);
    }

    private SearchLedgerEntriesResponseDto searchJournalEntries(SearchLedgerEntriesDto searchLedgerEntriesDto){
        AccountService accountService = new AccountService(request, null);

        SearchLedgerEntriesResponseDto searchLedgerEntriesResponseDto = accountService.searchJournalEntries(searchLedgerEntriesDto);
        return searchLedgerEntriesResponseDto;
    }

    @GetMapping("/supplierPaymentReport")
    public ResponseEntity<ResultResponse> supplierPaymentReport(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/printSuppliersBalance")
    public ResponseEntity<ResultResponse> printSuppliersBalance(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/balanceSuppliers")
    public ResponseEntity<ResultResponse> balanceSuppliers(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/printSupplierPayment")
    public ResponseEntity<SupplierPaymentDto> printSupplierPayment(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page) {
        SupplierPaymentDto paymentDto = new SupplierPaymentDto();

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page, branchId);
        paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
        paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
        paymentDto.setPage(suppliersPaymentResult.getPage());

        return ResponseEntity.ok(paymentDto);
    }

    @PostMapping("/cancelCheque")
    public ResponseEntity<SupplierPaymentDto> cancelCheque(@RequestBody ChequeDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestParam(value = "page") String page) {
        SupplierPaymentDto paymentDto = new SupplierPaymentDto();

        ResultResponse cancelChequeResult = messSuppliersService.cancelCheque(dto, branchId, userId);
        paymentDto.setResult(cancelChequeResult.isSuccess());

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page, branchId);
        paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
        paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
        paymentDto.setPage(suppliersPaymentResult.getPage());

        return ResponseEntity.ok(paymentDto);

    }

    @PostMapping("/clearedCheque")
    public ResponseEntity<SupplierPaymentDto> clearedCheque(@RequestBody ChequeDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestParam(value = "page") String page) {
        SupplierPaymentDto paymentDto = new SupplierPaymentDto();

        ResultResponse cancelChequeResult = messSuppliersService.clearedCheque(dto, branchId, userId);
        paymentDto.setResult(cancelChequeResult.isSuccess());

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page, branchId);
        paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
        paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
        paymentDto.setPage(suppliersPaymentResult.getPage());

        return ResponseEntity.ok(paymentDto);

    }

    @PostMapping("/deliveredCheque")
    public ResponseEntity<SupplierPaymentDto> deliveredCheque(@RequestBody ChequeDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page) {
        SupplierPaymentDto paymentDto = new SupplierPaymentDto();

        ResultResponse cancelChequeResult = messSuppliersService.deliveredCheque(dto, branchId);
        paymentDto.setResult(cancelChequeResult.isSuccess());

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page, branchId);
        paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
        paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
        paymentDto.setPage(suppliersPaymentResult.getPage());

        return ResponseEntity.ok(paymentDto);
    }

    @PostMapping("/issueCheque")
    public ResponseEntity<SupplierPaymentDto> issueCheque(@RequestBody ChequeDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId, @RequestParam(value = "page") String page) {
        SupplierPaymentDto paymentDto = new SupplierPaymentDto();

        ResultResponse cancelChequeResult = messSuppliersService.issueCheque(dto, branchId, userId);
        paymentDto.setResult(cancelChequeResult.isSuccess());

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page, branchId);
        paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
        paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
        paymentDto.setPage(suppliersPaymentResult.getPage());

        return ResponseEntity.ok(paymentDto);
    }

    //TODO : This need refactoring in order to return JSON Response.
    @GetMapping("/getSupplierBalance")
    public ResponseEntity getSupplierBalance(@RequestHeader(value = "supplierid") String supplierId) {
        try {
            messSuppliersService.getSupplierBalance(supplierId);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paymentSuppliers")
    public ResponseEntity<SupplierPaymentDto> paymentSuppliers(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "page") String page) {
        SupplierPaymentDto paymentDto = new SupplierPaymentDto();

        ResultResponse suppliersDetailsResult = messSuppliersService.viewSuppliersDetails(branchId);
        paymentDto.setMessSuppliersList(suppliersDetailsResult.getResultList());

        PaymentDetailsResponseDto suppliersPaymentResult = messSuppliersService.viewSuppliersPaymentDetails(page, branchId);
        paymentDto.setSupplierPaymentlist(suppliersPaymentResult.getSupplierPaymentList());
        paymentDto.setNoOfPages(suppliersPaymentResult.getNoOfPages());
        paymentDto.setPage(suppliersPaymentResult.getPage());

        return ResponseEntity.ok(paymentDto);
    }

    @PostMapping("/deleteSuppliers")
    public ResponseEntity<ResultResponse> deleteSuppliers(@RequestBody MessIdsDto dto, @RequestHeader(value = "branchid") String branchId) {
        messSuppliersService.deleteMultipleSuppliers(dto);
        return viewSuppliers(branchId);
    }

    @PostMapping("/updateSuppliers")
    public ResponseEntity<ResultResponse> updateSuppliers(@RequestBody MessIdsDto dto, @RequestHeader(value = "branchid") String branchId) {

        messSuppliersService.updateSuppliers(dto);
        return viewSuppliers(branchId);
    }

    @GetMapping("/viewSuppliers")
    public ResponseEntity<ResultResponse> viewSuppliers(@RequestHeader(value = "branchid") String branchId) {
        ResultResponse result = messSuppliersService.viewSuppliersDetails(branchId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        else
            throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    @PostMapping("/addSuppliers")
    public ResponseEntity<SuppliersDetailsResponseDto> addSuppliers(@RequestBody SuppliersDetailsDto dto, @RequestHeader(value = "branchid") String branchId, @RequestHeader(value = "userloginid") String userId) {

        SuppliersDetailsResponseDto responseDto = messSuppliersService.addSupplierDetails(dto, branchId, userId);
        ResultResponse result = viewSuppliers(branchId).getBody();
        responseDto.setMessSuppliersList(result.getResultList());

        return ResponseEntity.ok(responseDto);
    }
}
