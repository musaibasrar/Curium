package org.ideoholic.curium.model.account.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.model.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AccountApiActionImpl implements AccountApiAction {

    @Autowired
    private YearService yearService;

    @Autowired
    private AccountService accountService;

    public ResponseEntity<IncomeStatementResponseDto> incomeStatement(IncomeStatementDto dto, String branchId) {
        IncomeStatementResponseDto result = accountService.getIncomeStatement(dto, branchId);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<SearchLedgersEntriesDto> searchLedgerEntries(SearchLedgerEntriesDto dto, String branchId) {

        SearchLedgersEntriesDto result = new SearchLedgersEntriesDto();

        SearchLedgerEntriesResponseDto ledgerEntriesResult = accountService.searchJournalEntries(dto, branchId);
        result.setLedgerTransaction(ledgerEntriesResult.getLedgerTransaction());
        result.setLedgerName(ledgerEntriesResult.getLedgerName());
        result.setAccountId(ledgerEntriesResult.getAccountId());
        result.setFromDate(ledgerEntriesResult.getFromDate());
        result.setToDate(ledgerEntriesResult.getToDate());
        result.setVoucherType(ledgerEntriesResult.getVoucherType());

        ResultResponse allLedgersResult = accountService.getAllLedgers(branchId);
        result.setAccountDetails(allLedgersResult.getResultList());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> generalLedgerReport(String branchId) {
        ResultResponse result = accountService.getAllLedgers(branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> getSSGroupName(String branchId, String accountSubGroupMasterId) {

        try {
            ResultResponse result = accountService.getSSGroupNames(branchId, accountSubGroupMasterId);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> viewCancelledVouchers(String branchId) {
        ResultResponse result = accountService.viewCancelledVouchers(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> cancelVoucher(CancelVoucherDto dto) {
        ResultResponse result = accountService.cancelVoucher(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<TrialBalanceResponseDto> trialBalance(DayBookDto dto, String branchId) {
        TrialBalanceResponseDto result = accountService.trialBalance(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<ViewNextVoucherResponseDto> viewNextVoucher(ViewNextVoucherDto dto) {
        ViewNextVoucherResponseDto result = accountService.viewVouchers(dto);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ViewNextVoucherResponseDto> viewVoucherReceipt(int voucherType, String branchId, String fromDate, String toDate, String nextVoucher) {
        ViewNextVoucherResponseDto result = accountService.viewVouchers(voucherType, branchId, fromDate, toDate, nextVoucher);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<BalanceSheetResponseDto> balanceSheet(String branchId) {
        BalanceSheetResponseDto result = accountService.balanceSheet(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CreateVoucherResponseDto> saveJournal(AccountJournalDto dto, String branchId) {
        CreateVoucherResponseDto result = accountService.saveJournal(dto, branchId);
        if (result.isSuccess()) {
            return createVoucher(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CreateVoucherResponseDto> saveContra(AccountContraDto dto, String branchId) {
        CreateVoucherResponseDto result = accountService.saveContra(dto, branchId);
        if (result.isSuccess()) {
            return createVoucher(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CreateVoucherResponseDto> savePayment(AccountPaymentDto dto, String branchId) {
        CreateVoucherResponseDto result = accountService.savePayment(dto, branchId);
        if (result.isSuccess()) {
            return createVoucher(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CreateVoucherResponseDto> saveReceipt(AccountReceiptDto dto, String branchId) {
        CreateVoucherResponseDto result = accountService.saveReceipt(dto, branchId);
        if (result.isSuccess()) {
            return createVoucher(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CreateVoucherResponseDto> createVoucher(String branchId) {
        CreateVoucherResponseDto result = accountService.createVoucher(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CreateAccountResponseDto> deleteAccount(AccountDeleteDto dto, String branchId) {
        CreateAccountResponseDto result = accountService.deleteAccount(dto);
        if (result.isSuccess()) {
            return createAccount(branchId);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CreateAccountResponseDto> saveAccount(AccountDto dto, String branchId) {
        CreateAccountResponseDto result = accountService.saveAccount(dto, branchId);
        if (result.isSuccess()) {
            return createAccount(branchId);
        }

        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> getSubGroupNames(String branchId, String accountGroupMasterId) {

        try {
            ResultResponse result = accountService.getSubGroupNames(branchId, accountGroupMasterId);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<CreateAccountResponseDto> createAccount(String branchId) {
        CreateAccountResponseDto result = accountService.createAccount(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);

    }

    public ResponseEntity<CurrentFinancialYearResponseDto> getCurrentFinancialYear(String branchId) {
        CurrentFinancialYearResponseDto result = accountService.getCurrentFinancialYear(branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> saveFinancialYear(AccountFinancialYearDto dto, String branchId) {
        ResultResponse result = accountService.saveFinancialYear(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<CurrentAcademicYearResponseDto> updateYear() {
        CurrentAcademicYearResponseDto result = yearService.updateYear();
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<ResultResponse> downloadTrialBalance() {
        ResultResponse result = accountService.downloadTrialBalance();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }


    public ResponseEntity<ResultResponse> exportTrialBalance(ExportTrialBalanceDto dto) {
        ResultResponse result = accountService.exportTrialBalance(dto);
        return ResponseEntity.ok(result);
    }

    public String printTrialBalance() {
        return "trialbalanceprint";
    }

    public ResponseEntity<SearchLedgersEntriesDto> searchSingleLedgerEntries(String accountIds, String branchId, String ledgerName) {

        SearchLedgersEntriesDto result = new SearchLedgersEntriesDto();

        SearchSingleLedgerEntriesResponseDto ledgerEntriesResult = accountService.searchSingleLedgerEntries(accountIds, branchId, ledgerName);
        result.setLedgerTransaction(ledgerEntriesResult.getLedgerTransaction());
        result.setLedgerName(ledgerEntriesResult.getLedgerName());
        result.setAccountId(ledgerEntriesResult.getAccountId());
        result.setFromDate(ledgerEntriesResult.getFromDate());
        result.setToDate(ledgerEntriesResult.getToDate());

        ResultResponse allLedgersResult = accountService.getAllLedgers(branchId);
        result.setAccountDetails(allLedgersResult.getResultList());

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<SearchJournalEntriesResponseDto> printSearchLedgerEntries(PrintSearchJournalEntriesDto dto, String branchId) {
        SearchJournalEntriesResponseDto result = accountService.printSearchJournalEntries(dto, branchId);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<VoucherPrintResponseDto> voucherPrint(VoucherPrintDto dto, String branchId) {
        VoucherPrintResponseDto result = accountService.viewVouchersPrint(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> exportVoucher(ExportVoucherDto dto, String branchId) {
        ResultResponse result = accountService.exportVoucher(dto, branchId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<ResultResponse> downloadVoucherTransactions() {
        ResultResponse result = accountService.downloadVoucherTransactions();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        throw new CustomResponseException(CustomErrorMessage.ERROR);
    }

    public ResponseEntity<DayBookResponseDto> dayBook(DayBookDto dto, String branchId) {
        DayBookResponseDto result = accountService.getDayBook(dto, branchId);
        return ResponseEntity.ok(result);
    }
}
