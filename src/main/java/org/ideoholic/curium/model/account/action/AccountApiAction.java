package org.ideoholic.curium.model.account.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.account.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping({"api/v1/accountProcess", "api/v1/subGroupName"})
public interface AccountApiAction {
    
    @PostMapping("/incomeStatement")
    ResponseEntity<IncomeStatementResponseDto> incomeStatement(@RequestBody IncomeStatementDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/searchLedgerEntries")
    ResponseEntity<SearchLedgersEntriesDto> searchLedgerEntries(@RequestBody SearchLedgerEntriesDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/generalLedgerReport")
    ResponseEntity<ResultResponse> generalLedgerReport(@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/getSSGroupNames")
    ResponseEntity<ResultResponse> getSSGroupName(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "subgroupname") String accountSubGroupMasterId);

    @GetMapping("/viewCancelledVouchers")
    ResponseEntity<ResultResponse> viewCancelledVouchers(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/cancelVoucher")
    ResponseEntity<ResultResponse> cancelVoucher(@RequestBody CancelVoucherDto dto);

    @RequestMapping(value = "/trialBalance", method = {RequestMethod.GET, RequestMethod.POST})
    ResponseEntity<TrialBalanceResponseDto> trialBalance(@RequestBody DayBookDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/viewNextVoucher")
    ResponseEntity<ViewNextVoucherResponseDto> viewNextVoucher(@RequestBody ViewNextVoucherDto dto);

    @GetMapping("/viewVoucherReceipt")
    ResponseEntity<ViewNextVoucherResponseDto> viewVoucherReceipt(@RequestParam(value = "vouchertype") int voucherType, @RequestHeader(value = "branchid") String branchId, @RequestParam(value = "fromdate") String fromDate, @RequestParam(value = "todate") String toDate, @RequestParam(value = "voucher") String nextVoucher);

    @GetMapping("/balanceSheet")
    ResponseEntity<BalanceSheetResponseDto> balanceSheet(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/saveJournal")
    ResponseEntity<CreateVoucherResponseDto> saveJournal(@RequestBody AccountJournalDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/saveContra")
    ResponseEntity<CreateVoucherResponseDto> saveContra(@RequestBody AccountContraDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/savePayment")
    ResponseEntity<CreateVoucherResponseDto> savePayment(@RequestBody AccountPaymentDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/saveReceipt")
    ResponseEntity<CreateVoucherResponseDto> saveReceipt(@RequestBody AccountReceiptDto dto, @RequestHeader(value = "branchid") String branchId);
    
    @GetMapping("/createVoucher")
    ResponseEntity<CreateVoucherResponseDto> createVoucher(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/deleteAccount")
    ResponseEntity<CreateAccountResponseDto> deleteAccount(@RequestBody AccountDeleteDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/saveAccount")
    ResponseEntity<CreateAccountResponseDto> saveAccount(@RequestBody AccountDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/getSubGroupNames")
    ResponseEntity<ResultResponse> getSubGroupNames(@RequestHeader(value = "branchid") String branchId, @RequestParam(value = "groupname") String accountGroupMasterId);

    @GetMapping("/createAccount")
    ResponseEntity<CreateAccountResponseDto> createAccount(@RequestHeader(value = "branchid") String branchId);

    @GetMapping("/getCurrentFinancialYear")
    ResponseEntity<CurrentFinancialYearResponseDto> getCurrentFinancialYear(@RequestHeader(value = "branchid") String branchId);

    @PostMapping("/saveFinancialYear")
    ResponseEntity<ResultResponse> saveFinancialYear(@RequestBody AccountFinancialYearDto dto, @RequestHeader(value = "branchid") String branchId);

    @GetMapping("/updateYear")
    ResponseEntity<CurrentAcademicYearResponseDto> updateYear();

    @PostMapping("/downloadTrialBalance")
    ResponseEntity<ResultResponse> downloadTrialBalance();

    @PostMapping("/exportTrialBalance")
    ResponseEntity<ResultResponse> exportTrialBalance(@RequestBody ExportTrialBalanceDto dto);

    @PostMapping("/printTrialBalance")
    String printTrialBalance();

    @GetMapping("/searchSingleLedgerEntries")
    ResponseEntity<SearchLedgersEntriesDto> searchSingleLedgerEntries(@RequestParam(value = "accountid") String accountIds, @RequestHeader(value = "branchid") String branchId, @RequestParam(value = "ledgername") String ledgerName);

    @PostMapping("/printSearchLedgerEntries")
    ResponseEntity<SearchJournalEntriesResponseDto> printSearchLedgerEntries(@RequestBody PrintSearchJournalEntriesDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/voucherPrint")
    ResponseEntity<VoucherPrintResponseDto> voucherPrint(@RequestBody VoucherPrintDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/exportVoucher")
    ResponseEntity<ResultResponse> exportVoucher(@RequestBody ExportVoucherDto dto, @RequestHeader(value = "branchid") String branchId);

    @PostMapping("/downloadVoucherTransactions")
    ResponseEntity<ResultResponse> downloadVoucherTransactions();

    @PostMapping("/daybook")
    ResponseEntity<DayBookResponseDto> dayBook(@RequestBody DayBookDto dto, @RequestHeader(value = "branchid") String branchId);
}
