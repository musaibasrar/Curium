package org.ideoholic.curium.model.account.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AccountActionAdapter {

    @Autowired
    private HttpServletRequest request;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private AccountService accountService;
			
	String BRANCHID = "branchid";

    public boolean saveAccount() {

	AccountDto accountDto = new AccountDto();
	accountDto.setNewSubGroup(request.getParameter("newsubgroup"));
	accountDto.setNewSSGroup(request.getParameter("newssgroup"));
	accountDto.setSubGroupName(request.getParameter("subgroupname"));
	accountDto.setSsGroupName(request.getParameter("ssgroupname"));
	accountDto.setGroupName(request.getParameter("groupname"));
	accountDto.setAccountName(request.getParameter("accountname"));
	accountDto.setAccountCode(request.getParameter("accountcode"));

		CreateAccountResponseDto response = accountService.saveAccount(accountDto, httpSession.getAttribute(BRANCHID).toString());
	if (response == null) {
	    return false;
	}

	request.setAttribute("createaccountalert", response.getMessage());

	return response.isSuccess();
    }

	public boolean deleteAccount(){
	
		AccountDeleteDto accountDeleteDto = new AccountDeleteDto();
		accountDeleteDto.setAccountIds(request.getParameterValues("accountids"));

		CreateAccountResponseDto resultResponse = accountService.deleteAccount(accountDeleteDto);

		return resultResponse.isSuccess();
	}

	public boolean saveReceipt(){
	
		AccountReceiptDto accountReceiptDto = new AccountReceiptDto();
		accountReceiptDto.setDraccountName(request.getParameter("accountname"));
		accountReceiptDto.setCraccountName(request.getParameter("accountnamesecond"));
		accountReceiptDto.setReceiptVoucher(request.getParameter("receiptvoucher"));
		accountReceiptDto.setDrAmount(request.getParameter("dramount"));
		accountReceiptDto.setCrAmount(request.getParameter("cramountsecond"));
		accountReceiptDto.setReceiptDate(request.getParameter("dateofreceipt"));
		accountReceiptDto.setReceiptNarration(request.getParameter("receiptnarration"));

		CreateVoucherResponseDto resultResponse = accountService.saveReceipt(accountReceiptDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean cancelVoucher(){
	
		CancelVoucherDto cancelVoucherDto = new CancelVoucherDto();
		cancelVoucherDto.setReceiptIds(request.getParameterValues("transactionids"));
		cancelVoucherDto.setVoucher(request.getParameter("voucher"));

		ResultResponse resultResponse = accountService.cancelVoucher(cancelVoucherDto);

		return resultResponse.isSuccess();
	}

	public boolean saveJournal(){
	
		AccountJournalDto accountJournalDto = new AccountJournalDto();
		accountJournalDto.setDraccountNameJournal(request.getParameter("accountnamejournal"));
		accountJournalDto.setCraccountNameJournal(request.getParameter("accountnamejournalsecond"));
		accountJournalDto.setJournalVoucher(request.getParameter("journalvoucher"));
		accountJournalDto.setDrAmountJournal(request.getParameter("dramountjournal"));
		accountJournalDto.setCrAmountJournal(request.getParameter("cramountjournalsecond"));
		accountJournalDto.setJournalDate(request.getParameter("dateofjournal"));
		accountJournalDto.setJournalNarration(request.getParameter("journalnarration"));

		CreateVoucherResponseDto resultResponse = accountService.saveJournal(accountJournalDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean saveContra(){
	
		AccountContraDto accountContraDto = new AccountContraDto();
		accountContraDto.setDraccountName(request.getParameter("accountnamecontra"));
		accountContraDto.setCraccountName(request.getParameter("accountnamecontrasecond"));
		accountContraDto.setContraVoucher(request.getParameter("contravoucher"));
		accountContraDto.setDrAmountContra(request.getParameter("dramountcontra"));
		accountContraDto.setCrAmountContra(request.getParameter("cramountcontrasecond"));
		accountContraDto.setContraDate(request.getParameter("dateofcontra"));
		accountContraDto.setContraNarration(request.getParameter("contranarration"));

		CreateVoucherResponseDto resultResponse = accountService.saveContra(accountContraDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean savePayment(){
	
		AccountPaymentDto accountPaymentDto = new AccountPaymentDto();
		accountPaymentDto.setDraccountName(request.getParameter("accountnamepayment"));
		accountPaymentDto.setCraccountName(request.getParameter("accountnamepaymentsecond"));
		accountPaymentDto.setPaymentVoucher(request.getParameter("paymentvoucher"));
		accountPaymentDto.setDrAmountPayment(request.getParameter("dramountpayment"));
		accountPaymentDto.setCrAmountPayment(request.getParameter("cramountpaymentsecond"));
		accountPaymentDto.setPaymentDate(request.getParameter("dateofpayment"));
		accountPaymentDto.setPaymentNarration(request.getParameter("paymentnarration"));

		CreateVoucherResponseDto resultResponse = accountService.savePayment(accountPaymentDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean saveFinancialYear(){
	
		AccountFinancialYearDto financialYearDto = new AccountFinancialYearDto();
		financialYearDto.setFromDate(request.getParameter("fromdate"));
		financialYearDto.setToDate(request.getParameter("todate"));
		financialYearDto.setActive(request.getParameter("active"));

		ResultResponse resultResponse = accountService.saveFinancialYear(financialYearDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean getIncomeStatement(){
	
		IncomeStatementDto incomeStatementDto = new IncomeStatementDto();
		incomeStatementDto.setFromDate(request.getParameter("fromdate"));
		incomeStatementDto.setToDate(request.getParameter("todate"));

		IncomeStatementResponseDto responseDto =  accountService.getIncomeStatement(incomeStatementDto, httpSession.getAttribute(BRANCHID).toString());
		Map<Accountdetails,BigDecimal> incomeLedgersAccount = new HashMap<>();
		Map<Accountdetails,BigDecimal> expenseLedgersAccount = new HashMap<>();
		for(LedgerAccBalanceDto accBalanceDto: responseDto.getIncomeLedgersAccount()) {
			incomeLedgersAccount.put(accBalanceDto.getAccountdetails(), accBalanceDto.getBalance());
		}
		for(LedgerAccBalanceDto accBalanceDto: responseDto.getExpenseLedgersAccount()) {
			expenseLedgersAccount.put(accBalanceDto.getAccountdetails(), accBalanceDto.getBalance());
		}

		request.setAttribute("income", responseDto.getIncome());
		request.setAttribute("incomeledgersaccount", incomeLedgersAccount);
		request.setAttribute("expenseledgersaccount", expenseLedgersAccount);
		request.setAttribute("expenses", responseDto.getExpenses());
		request.setAttribute("incometotallabel", responseDto.getIncomeTotalLabel());
		request.setAttribute("expensetotallabel", responseDto.getExpenseTotalLabel());
		request.setAttribute("incometotal", responseDto.getIncomeTotal());
		request.setAttribute("expensetotal", responseDto.getExpenseTotal());
		request.setAttribute("fromdate", request.getParameter("fromdate"));
		request.setAttribute("todate", request.getParameter("todate"));
		request.setAttribute("profitlabel", responseDto.getProfitLabel());
		request.setAttribute("totalprofit", responseDto.getTotalProfit());
		request.setAttribute("losslabel", responseDto.getLossLabel());
		request.setAttribute("totalloss", responseDto.getTotalLoss());

		return responseDto.isSuccess();
	}

	public boolean exportTrialBalance(){
	
		ExportTrialBalanceDto exportTrialBalanceDto = new ExportTrialBalanceDto();
		exportTrialBalanceDto.setCreditAllAcc(httpSession.getAttribute("credittotal").toString());
		exportTrialBalanceDto.setDebitAllAcc(httpSession.getAttribute("debittotal").toString());
		exportTrialBalanceDto.setFromDate((String) httpSession.getAttribute("fromdatetb"));
		exportTrialBalanceDto.setToDate((String) httpSession.getAttribute("todatetb"));
		Map<String, TrailBalanceDto> trailBalanceDto = new LinkedHashMap<String, TrailBalanceDto>();
		Map<Accountdetails, BigDecimal> accountBalanceMap = (Map<Accountdetails, BigDecimal >)httpSession.getAttribute("accountdetailsbalanceMap");
		String account = "account";
		int count = 1;

		for (Map.Entry<Accountdetails, BigDecimal> accBal : accountBalanceMap.entrySet()) {
			trailBalanceDto.put(account+count++, TrailBalanceDto.builder()
					.accountDetails(accBal.getKey())
					.amount(accBal.getValue())
					.build());

		}
		exportTrialBalanceDto.setTrailBalanceDto(trailBalanceDto);
		ResultResponse resultResponse = accountService.exportTrialBalance(exportTrialBalanceDto);

		return resultResponse.isSuccess();
	}

	public boolean printSearchJournalEntries() {
	
		PrintSearchJournalEntriesDto printSearchJournalEntriesDto = new PrintSearchJournalEntriesDto();
		printSearchJournalEntriesDto.setAccountDetails(request.getParameter("accountidselected"));
		printSearchJournalEntriesDto.setFromDate(request.getParameter("fromdateselected"));
		printSearchJournalEntriesDto.setToDate(request.getParameter("todateselected"));

		SearchJournalEntriesResponseDto responseDto = accountService.printSearchJournalEntries(printSearchJournalEntriesDto, httpSession.getAttribute(BRANCHID).toString());
		if (responseDto.isSuccess()){
			Map resultMap = responseDto.getLedgerTransaction();
			request.setAttribute("ledgertransactions", responseDto.getLedgerTransaction());
			request.setAttribute("ledgername", responseDto.getLedgerName());
			request.setAttribute("fromdateselected", responseDto.getFromDate());
			request.setAttribute("todateselected", responseDto.getToDate());
		}

		return responseDto.isSuccess();
	}

	public boolean viewVouchersPrint(){
	
		VoucherPrintDto voucherPrintDto = new VoucherPrintDto();
		voucherPrintDto.setFromDate(request.getParameter("fromdateselected"));
		voucherPrintDto.setToDate(request.getParameter("todateselected"));
		voucherPrintDto.setNextVoucher(request.getParameter("voucher"));

		VoucherPrintResponseDto voucherPrintResponseDto = accountService.viewVouchersPrint(voucherPrintDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("vouchertransactions", voucherPrintResponseDto.getVoucherTransactions());
		request.setAttribute("vouchertype", voucherPrintResponseDto.getVoucherType());
		request.setAttribute("fromdateselected", voucherPrintResponseDto.getFromDateSelected());
		request.setAttribute("todateselected", voucherPrintResponseDto.getToDateSelected());

		return accountService.viewVouchersPrint(voucherPrintDto, httpSession.getAttribute(BRANCHID).toString()).isSuccess();
	}

	public boolean viewVouchers(){
	
		ViewNextVoucherDto viewNextVoucherDto = new ViewNextVoucherDto();
		viewNextVoucherDto.setFromDate(request.getParameter("fromdate"));
		viewNextVoucherDto.setToDate(request.getParameter("todate"));
		viewNextVoucherDto.setNextVoucher(request.getParameter("voucher"));
		viewNextVoucherDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		ViewNextVoucherResponseDto viewNextVoucherResponseDto = accountService.viewVouchers(viewNextVoucherDto);
		request.setAttribute("vouchertransactions", viewNextVoucherResponseDto.getVoucherTransactions());
		request.setAttribute("vouchertype", viewNextVoucherResponseDto.getVoucherType());
		request.setAttribute("fromdateselected", viewNextVoucherResponseDto.getFromDateSelected());
		request.setAttribute("todateselected", viewNextVoucherResponseDto.getToDateSelected());

		return accountService.viewVouchers(viewNextVoucherDto).isSuccess();

	}

	public boolean searchJournalEntries(){
	
		SearchLedgerEntriesDto searchLedgerEntriesDto = new SearchLedgerEntriesDto();
		searchLedgerEntriesDto.setAccountDetails(request.getParameter("accountid"));
		searchLedgerEntriesDto.setFromDate(request.getParameter("fromdate"));
		searchLedgerEntriesDto.setToDate(request.getParameter("todate"));

		SearchLedgerEntriesResponseDto searchLedgerEntriesResponseDto = accountService.searchJournalEntries(searchLedgerEntriesDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("ledgertransactions", searchLedgerEntriesResponseDto.getLedgerTransaction());
		request.setAttribute("ledgername", searchLedgerEntriesResponseDto.getLedgerName());
		request.setAttribute("accountid", searchLedgerEntriesResponseDto.getAccountId());
		request.setAttribute("fromdate", searchLedgerEntriesResponseDto.getFromDate());
		request.setAttribute("todate", searchLedgerEntriesResponseDto.getToDate());

		return searchLedgerEntriesResponseDto.isSuccess();
	}

	public boolean getAllLedgers() {
			ResultResponse resultResponse = accountService.getAllLedgers(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("ledgeraccountdetails", resultResponse.getResultList());

		return resultResponse.isSuccess();
	}

	public boolean exportVoucher(){
	
		ExportVoucherDto exportVoucherDto = new ExportVoucherDto();
		exportVoucherDto.setNextVoucher(request.getParameter("voucher"));
		exportVoucherDto.setFromDate(request.getParameter("fromdateselected"));
		exportVoucherDto.setToDate(request.getParameter("todateselected"));

		ResultResponse resultResponse =accountService.exportVoucher(exportVoucherDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("vouchertype", resultResponse.getMessage());

		return resultResponse.isSuccess();
	}

	public boolean downloadTrialBalance() {
	
		ResultResponse resultResponse = accountService.downloadTrialBalance();

		return resultResponse.isSuccess();
	}

	public boolean downloadVoucherTransactions() {
	
		ResultResponse resultResponse = accountService.downloadVoucherTransactions();

		return resultResponse.isSuccess();
	}

	public boolean searchSingleLedgerEntries() {
	
		String accountIds = request.getParameter("accountid");
		String ledgerName = request.getParameter("ledgername");

		SearchSingleLedgerEntriesResponseDto searchSingleLedgerEntriesResponseDto = accountService.searchSingleLedgerEntries(accountIds, httpSession.getAttribute(BRANCHID).toString(), ledgerName);
		request.setAttribute("ledgertransactions", searchSingleLedgerEntriesResponseDto.getLedgerTransaction());
		request.setAttribute("ledgername", searchSingleLedgerEntriesResponseDto.getLedgerName());
		request.setAttribute("accountid", searchSingleLedgerEntriesResponseDto.getAccountId());
		request.setAttribute("fromdate", searchSingleLedgerEntriesResponseDto.getFromDate());
		request.setAttribute("todate", searchSingleLedgerEntriesResponseDto.getToDate());

		return searchSingleLedgerEntriesResponseDto.isSuccess();
	}

	public boolean viewCancelledVouchers() {
	
		ResultResponse resultResponse = accountService.viewCancelledVouchers(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("cancelledvouchertransactions", resultResponse.getResultMap());

		return resultResponse.isSuccess();
	}
	public void viewVouchers(int voucherType){
	
		String fromDate = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");
		String nextVoucher = request.getParameter("voucher");

		ViewNextVoucherResponseDto viewNextVoucherResponseDto = accountService.viewVouchers(voucherType, httpSession.getAttribute(BRANCHID).toString(), fromDate, toDate, nextVoucher);
		request.setAttribute("vouchertransactions", viewNextVoucherResponseDto.getVoucherTransactions());
		request.setAttribute("vouchertype", viewNextVoucherResponseDto.getVoucherType());
		request.setAttribute("fromdateselected", viewNextVoucherResponseDto.getFromDateSelected());
		request.setAttribute("todateselected", viewNextVoucherResponseDto.getToDateSelected());


	}

	public boolean balanceSheet() {
	
		BalanceSheetResponseDto balanceSheetResponseDto = accountService.balanceSheet(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("liabilities", balanceSheetResponseDto.getLiabilities());
		request.setAttribute("liabilitiesLedgeraccount", balanceSheetResponseDto.getLiabilitiesLedgerAccount());
		request.setAttribute("reserves", balanceSheetResponseDto.getReserves());
		request.setAttribute("reservesLedgeraccount", balanceSheetResponseDto.getReservesLedgerAccount());
		request.setAttribute("assets", balanceSheetResponseDto.getAssets());
		request.setAttribute("assetsLedgeraccount", balanceSheetResponseDto.getAssetsLedgerAccount());
		request.setAttribute("grouponetotal", balanceSheetResponseDto.getLiabilities());
		request.setAttribute("grouponetotalreserves", balanceSheetResponseDto.getReserves());
		request.setAttribute("grouptwototal", balanceSheetResponseDto.getAssets());

		return balanceSheetResponseDto.isSuccess();
	}

	public boolean createVoucher() {
	
		CreateVoucherResponseDto createVoucherResponseDto = accountService.createVoucher(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("accountdetailsbalanceexbc", createVoucherResponseDto.getAccountDetailsBalance());
		request.setAttribute("accountdetailsbalanceexpacc", createVoucherResponseDto.getAccountDetailsBalanceExpenses());
		request.setAttribute("accountdetailsbalancecontra",createVoucherResponseDto.getAccountDetailsBalanceBankCash());
		request.setAttribute("accountdetailsbalancereceipt", createVoucherResponseDto.getAccountDetailsBalanceBankCash());
		request.setAttribute("accountdetailsbalancepayment", createVoucherResponseDto.getAccountDetailsBalanceBankCash());
		request.setAttribute("accountdetailsbalancejournal", createVoucherResponseDto.getAccountDetailsJournalEntry());

		return createVoucherResponseDto.isSuccess();
	}

	public void getSSGroupNames() throws IOException {
	
		String strAccountSubGroupMasterId = request.getParameter("subgroupname");

		ResultResponse resultResponse = accountService.getSSGroupNames(httpSession.getAttribute(BRANCHID).toString(), strAccountSubGroupMasterId);
		if(resultResponse != null && resultResponse.getResultList() != null){
			request.setAttribute("accountssgroupmaster", resultResponse.getResultList());
		}
	}

	public void getSubGroupNames() throws IOException {
	
		String strAccountGroupMasterId = request.getParameter("groupname");

		ResultResponse resultResponse = accountService.getSubGroupNames(httpSession.getAttribute(BRANCHID).toString(), strAccountGroupMasterId);
		if(resultResponse != null && resultResponse.getResultList() != null){
			request.setAttribute("accountsubgroupmaster", resultResponse.getResultList());
		}
	}

	public boolean createAccount() {
	
		CreateAccountResponseDto createAccountResponseDto = accountService.createAccount(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("accountgroupmaster", createAccountResponseDto.getAccountGroupMaster());
		request.setAttribute("accountdetailsbalance", createAccountResponseDto.getAccountDetailsBalance());

		return createAccountResponseDto.isSuccess();
	}

	public boolean getCurrentFinancialYear() {
	
		CurrentFinancialYearResponseDto currentFinancialYearResponseDto = accountService.getCurrentFinancialYear(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("currentfinancialaccountingyearfrom", currentFinancialYearResponseDto.getFinancialStartDate());
		request.setAttribute("currentfinancialaccountingyearto", currentFinancialYearResponseDto.getFinancialEndDate());

		return currentFinancialYearResponseDto.isSuccess();
	}

	public boolean trialBalance() {

		DayBookDto dto = new DayBookDto();
		dto.setFromDate(request.getParameter("fromdate"));
		dto.setToDate(request.getParameter("todate"));

		TrialBalanceResponseDto trialBalanceResponseDto = accountService.trialBalance(dto, httpSession.getAttribute(BRANCHID).toString());
		httpSession.setAttribute("accountdetailsbalanceMap", trialBalanceResponseDto.getAccountDetailsBalanceMap());
		httpSession.setAttribute("credittotal", trialBalanceResponseDto.getCreditTotal());
		httpSession.setAttribute("debittotal", trialBalanceResponseDto.getDebitTotal());
		httpSession.setAttribute("fromdatetb", trialBalanceResponseDto.getFromDate());
		httpSession.setAttribute("todatetb", trialBalanceResponseDto.getToDate());

		return trialBalanceResponseDto.isSuccess();
	}
	
	public boolean getDayBook(){

		DayBookDto dto = new DayBookDto();
		dto.setFromDate(DateUtil.dateFromatConversionSlash(request.getParameter("fromdate")));
		dto.setToDate(DateUtil.dateFromatConversionSlash(request.getParameter("todate")));
		
		DayBookResponseDto dayBookDtoOutput = accountService.getDayBook(dto,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("voucherentrytransactions", dayBookDtoOutput.getVoucherEntryTransactions());
		
		return dayBookDtoOutput.isSuccess();
	}
}
