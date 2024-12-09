package org.ideoholic.curium.model.account.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Service
public class AccountActionAdapter {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

	String BRANCHID = "branchid";

    public boolean saveAccount() {
	AccountService accountService = new AccountService(request, response);

	AccountDto accountDto = new AccountDto();
	accountDto.setNewSubGroup(request.getParameter("newsubgroup"));
	accountDto.setNewSSGroup(request.getParameter("newssgroup"));
	accountDto.setSubGroupName(request.getParameter("subgroupname"));
	accountDto.setSsGroupName(request.getParameter("ssgroupname"));
	accountDto.setGroupName(request.getParameter("groupname"));
	accountDto.setAccountName(request.getParameter("accountname"));
	accountDto.setAccountCode(request.getParameter("accountcode"));

	ResultResponse response = accountService.saveAccount(accountDto, httpSession.getAttribute(BRANCHID).toString());
	if (response == null) {
	    return false;
	}

	request.setAttribute("createaccountalert", response.getMessage());

	return response.isSuccess();
    }

	public boolean deleteAccount(){
		AccountService accountService = new AccountService(request, response);

		AccountDeleteDto accountDeleteDto = new AccountDeleteDto();
		accountDeleteDto.setAccountIds(request.getParameterValues("accountids"));

		ResultResponse resultResponse = accountService.deleteAccount(accountDeleteDto);

		return resultResponse.isSuccess();
	}

	public boolean saveReceipt(){
		AccountService accountService = new AccountService(request, response);

		AccountReceiptDto accountReceiptDto = new AccountReceiptDto();
		accountReceiptDto.setDraccountName(request.getParameter("accountname"));
		accountReceiptDto.setCraccountName(request.getParameter("accountnamesecond"));
		accountReceiptDto.setReceiptVoucher(request.getParameter("receiptvoucher"));
		accountReceiptDto.setDrAmount(request.getParameter("dramount"));
		accountReceiptDto.setCrAmount(request.getParameter("cramountsecond"));
		accountReceiptDto.setReceiptDate(request.getParameter("dateofreceipt"));
		accountReceiptDto.setReceiptNarration(request.getParameter("receiptnarration"));

		ResultResponse resultResponse = accountService.saveReceipt(accountReceiptDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean cancelVoucher(){
		AccountService accountService = new AccountService(request, response);

		CancelVoucherDto cancelVoucherDto = new CancelVoucherDto();
		cancelVoucherDto.setReceiptIds(request.getParameterValues("transactionids"));
		cancelVoucherDto.setVoucher(request.getParameter("voucher"));

		ResultResponse resultResponse = accountService.cancelVoucher(cancelVoucherDto);

		return resultResponse.isSuccess();
	}

	public boolean saveJournal(){
		AccountService accountService = new AccountService(request, response);

		AccountJournalDto accountJournalDto = new AccountJournalDto();
		accountJournalDto.setDraccountNameJournal(request.getParameter("accountnamejournal"));
		accountJournalDto.setCraccountNameJournal(request.getParameter("accountnamejournalsecond"));
		accountJournalDto.setJournalVoucher(request.getParameter("journalvoucher"));
		accountJournalDto.setDrAmountJournal(request.getParameter("dramountjournal"));
		accountJournalDto.setCrAmountJournal(request.getParameter("cramountjournalsecond"));
		accountJournalDto.setJournalDate(request.getParameter("dateofjournal"));
		accountJournalDto.setJournalNarration(request.getParameter("journalnarration"));

		ResultResponse resultResponse = accountService.saveJournal(accountJournalDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean saveContra(){
		AccountService accountService = new AccountService(request, response);

		AccountContraDto accountContraDto = new AccountContraDto();
		accountContraDto.setDraccountName(request.getParameter("accountnamecontra"));
		accountContraDto.setCraccountName(request.getParameter("accountnamecontrasecond"));
		accountContraDto.setContraVoucher(request.getParameter("contravoucher"));
		accountContraDto.setDrAmountContra(request.getParameter("dramountcontra"));
		accountContraDto.setCrAmountContra(request.getParameter("cramountcontrasecond"));
		accountContraDto.setContraDate(request.getParameter("dateofcontra"));
		accountContraDto.setContraNarration(request.getParameter("contranarration"));

		ResultResponse resultResponse = accountService.saveContra(accountContraDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean savePayment(){
		AccountService accountService = new AccountService(request, response);

		AccountPaymentDto accountPaymentDto = new AccountPaymentDto();
		accountPaymentDto.setDraccountName(request.getParameter("accountnamepayment"));
		accountPaymentDto.setCraccountName(request.getParameter("accountnamepaymentsecond"));
		accountPaymentDto.setPaymentVoucher(request.getParameter("paymentvoucher"));
		accountPaymentDto.setDrAmountPayment(request.getParameter("dramountpayment"));
		accountPaymentDto.setCrAmountPayment(request.getParameter("cramountpaymentsecond"));
		accountPaymentDto.setPaymentDate(request.getParameter("dateofpayment"));
		accountPaymentDto.setPaymentNarration(request.getParameter("paymentnarration"));

		ResultResponse resultResponse = accountService.savePayment(accountPaymentDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean saveFinancialYear(){
		AccountService accountService = new AccountService(request, response);

		AccountFinancialYearDto financialYearDto = new AccountFinancialYearDto();
		financialYearDto.setFromDate(request.getParameter("fromdate"));
		financialYearDto.setToDate(request.getParameter("todate"));
		financialYearDto.setActive(request.getParameter("active"));

		ResultResponse resultResponse = accountService.saveFinancialYear(financialYearDto, httpSession.getAttribute(BRANCHID).toString());

		return resultResponse.isSuccess();
	}

	public boolean getIncomeStatement(){
		AccountService accountService = new AccountService(request, response);

		IncomeStatementDto incomeStatementDto = new IncomeStatementDto();
		incomeStatementDto.setFromDate(request.getParameter("fromdate"));
		incomeStatementDto.setToDate(request.getParameter("todate"));

		IncomeStatementResponseDto responseDto =  accountService.getIncomeStatement(incomeStatementDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("income", responseDto.getIncome());
		request.setAttribute("incomeledgersaccount", responseDto.getIncomeLedgersAccount());
		request.setAttribute("expenses", responseDto.getExpenses());
		request.setAttribute("incometotallabel", responseDto.getIncomeTotalLabel());
		request.setAttribute("expensetotallabel", responseDto.getExpenseTotalLabel());
		request.setAttribute("incometotal", responseDto.getIncomeTotal());
		request.setAttribute("expensetotal", responseDto.getExpenseTotal());
		request.setAttribute("fromdate", responseDto.getFromDate());
		request.setAttribute("todate", responseDto.getToDate());
		request.setAttribute("profitlabel", responseDto.getProfitLabel());
		request.setAttribute("totalprofit", responseDto.getTotalProfit());
		request.setAttribute("losslabel", responseDto.getLossLabel());
		request.setAttribute("totalloss", responseDto.getTotalLoss());

		return responseDto.isSuccess();
	}

	public boolean exportTrialBalance(){
		AccountService accountService = new AccountService(request, response);

		ExportTrialBalanceDto exportTrialBalanceDto = new ExportTrialBalanceDto();
		exportTrialBalanceDto.setCreditAllAcc(httpSession.getAttribute("credittotal").toString());
		exportTrialBalanceDto.setDebitAllAcc(httpSession.getAttribute("debittotal").toString());
		exportTrialBalanceDto.setFromDate((String) httpSession.getAttribute("fromdatetb"));
		exportTrialBalanceDto.setToDate((String) httpSession.getAttribute("todatetb"));

		ResultResponse resultResponse = accountService.exportTrialBalance(exportTrialBalanceDto,  httpSession.getAttribute("accountdetailsbalanceMap").toString());

		return resultResponse.isSuccess();
	}

	public boolean printSearchJournalEntries() {
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);
		ResultResponse resultResponse = accountService.getAllLedgers(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("ledgeraccountdetails", resultResponse.getResultList());

		return resultResponse.isSuccess();
	}

	public boolean exportVoucher(){
		AccountService accountService = new AccountService(request, response);

		ExportVoucherDto exportVoucherDto = new ExportVoucherDto();
		exportVoucherDto.setNextVoucher(request.getParameter("voucher"));
		exportVoucherDto.setFromDate(request.getParameter("fromdateselected"));
		exportVoucherDto.setToDate(request.getParameter("todateselected"));

		ResultResponse resultResponse =accountService.exportVoucher(exportVoucherDto, httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("vouchertype", resultResponse.getMessage());

		return resultResponse.isSuccess();
	}

	public boolean downloadTrialBalance() {
		AccountService accountService = new AccountService(request, response);

		ResultResponse resultResponse = accountService.downloadTrialBalance();

		return resultResponse.isSuccess();
	}

	public boolean downloadVoucherTransactions() {
		AccountService accountService = new AccountService(request, response);

		ResultResponse resultResponse = accountService.downloadVoucherTransactions();

		return resultResponse.isSuccess();
	}

	public boolean searchSingleLedgerEntries() {
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);

		ResultResponse resultResponse = accountService.viewCancelledVouchers(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("cancelledvouchertransactions", resultResponse.getResultMap());

		return resultResponse.isSuccess();
	}
	public void viewVouchers(int voucherType){
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);

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
		AccountService accountService = new AccountService(request, response);

		String strAccountSubGroupMasterId = request.getParameter("subgroupname");

		ResultResponse resultResponse = accountService.getSSGroupNames(httpSession.getAttribute(BRANCHID).toString(), strAccountSubGroupMasterId);
		if(resultResponse != null && resultResponse.getResultList() != null){
			request.setAttribute("accountssgroupmaster", resultResponse.getResultList());
		}
	}

	public void getSubGroupNames() throws IOException {
		AccountService accountService = new AccountService(request, response);

		String strAccountGroupMasterId = request.getParameter("groupname");

		ResultResponse resultResponse = accountService.getSubGroupNames(httpSession.getAttribute(BRANCHID).toString(), strAccountGroupMasterId);
		if(resultResponse != null && resultResponse.getResultList() != null){
			request.setAttribute("accountsubgroupmaster", resultResponse.getResultList());
		}
	}

	public boolean createAccount() {
		AccountService accountService = new AccountService(request, response);

		CreateAccountResponseDto createAccountResponseDto = accountService.createAccount(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("accountgroupmaster", createAccountResponseDto.getAccountGroupMaster());
		request.setAttribute("accountdetailsbalance", createAccountResponseDto.getAccountDetailsBalance());

		return createAccountResponseDto.isSuccess();
	}

	public boolean getCurrentFinancialYear() {
		AccountService accountService = new AccountService(request, response);

		CurrentFinancialYearResponseDto currentFinancialYearResponseDto = accountService.getCurrentFinancialYear(httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("currentfinancialaccountingyearfrom", currentFinancialYearResponseDto.getFinancialStartDate());
		request.setAttribute("currentfinancialaccountingyearto", currentFinancialYearResponseDto.getFinancialEndDate());

		return currentFinancialYearResponseDto.isSuccess();
	}

	public boolean trialBalance() {
		AccountService accountService = new AccountService(request, response);

		String strFromDate = request.getParameter("fromdate");
		String strToDate = request.getParameter("todate");

		TrialBalanceResponseDto trialBalanceResponseDto = accountService.trialBalance(strFromDate, strToDate, httpSession.getAttribute(BRANCHID).toString());
		httpSession.setAttribute("accountdetailsbalanceMap", trialBalanceResponseDto.getAccountDetailsBalanceMap());
		httpSession.setAttribute("credittotal", trialBalanceResponseDto.getCreditTotal());
		httpSession.setAttribute("debittotal", trialBalanceResponseDto.getDebitTotal());
		httpSession.setAttribute("fromdatetb", trialBalanceResponseDto.getFromDate());
		httpSession.setAttribute("todatetb", trialBalanceResponseDto.getToDate());

		return trialBalanceResponseDto.isSuccess();
	}
	
	public boolean getDayBook(){
		AccountService accountService = new AccountService(request, response);

		String strFromDate = DateUtil.dateFromatConversionSlash(request.getParameter("fromdate"));
		String strToDate = DateUtil.dateFromatConversionSlash(request.getParameter("todate"));
		
		DayBookDto dayBookDtoOutput = accountService.getDayBook(strFromDate,strToDate,httpSession.getAttribute(BRANCHID).toString());
		request.setAttribute("voucherentrytransactions", dayBookDtoOutput.getVoucherEntryTransactions());
		
		return dayBookDtoOutput.isSuccess();
	}
}
