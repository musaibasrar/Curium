package org.ideoholic.curium.model.account.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.account.dto.*;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	ResultResponse response = accountService.saveAccount(accountDto);
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

		return accountService.deleteAccount(accountDeleteDto);
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

		return accountService.saveReceipt(accountReceiptDto);
	}

	public boolean cancelVoucher(){
		AccountService accountService = new AccountService(request, response);

		CancelVoucherDto cancelVoucherDto = new CancelVoucherDto();
		cancelVoucherDto.setReceiptIds(request.getParameterValues("transactionids"));
		cancelVoucherDto.setVoucher(request.getParameter("voucher"));

		return accountService.cancelVoucher(cancelVoucherDto);
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

		return accountService.saveJournal(accountJournalDto);
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

		return accountService.saveContra(accountContraDto);
	}

	public boolean savePayment(){
		AccountService accountService = new AccountService(request, response);

		AccountPaymentDto accountPaymentDto = new AccountPaymentDto();
		accountPaymentDto.setDraccountName(request.getParameter("accountnamepayment"));
		accountPaymentDto.setCraccountName(request.getParameter("accountnamepaymentsecond"));
		accountPaymentDto.setPaymentVoucher(request.getParameter("paymentavoucher"));
		accountPaymentDto.setDrAmountPayment(request.getParameter("dramountpayment"));
		accountPaymentDto.setCrAmountPayment(request.getParameter("cramountpaymentsecond"));
		accountPaymentDto.setPaymentDate(request.getParameter("dateofpayment"));
		accountPaymentDto.setPaymentNarration(request.getParameter("paymentnarration"));

		return accountService.savePayment(accountPaymentDto);

	}

	public boolean saveFinancialYear(){
		AccountService accountService = new AccountService(request, response);

		AccountFinancialYearDto financialYearDto = new AccountFinancialYearDto();
		financialYearDto.setFromDate(request.getParameter("fromdate"));
		financialYearDto.setToDate(request.getParameter("todate"));
		financialYearDto.setActive(request.getParameter("active"));
		financialYearDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));


		return accountService.saveFinancialYear(financialYearDto);
	}

	public boolean getIncomeStatement(){
		AccountService accountService = new AccountService(request, response);

		IncomeStatementDto incomeStatementDto = new IncomeStatementDto();
		incomeStatementDto.setFromDate(request.getParameter("fromdate"));
		incomeStatementDto.setToDate(request.getParameter("todate"));
		incomeStatementDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		return accountService.getIncomeStatement(incomeStatementDto);

	}

	public boolean exportTrialBalance(){
		AccountService accountService = new AccountService(request, response);

		ExportTrialBalanceDto exportTrialBalanceDto = new ExportTrialBalanceDto();
		exportTrialBalanceDto.setCreditAllAcc(httpSession.getAttribute("credittotal").toString());
		exportTrialBalanceDto.setDebitAllAcc(httpSession.getAttribute("debittotal").toString());
		exportTrialBalanceDto.setFromDate((String) httpSession.getAttribute("fromdatetb"));
		exportTrialBalanceDto.setToDate((String) httpSession.getAttribute("todatetb"));

		return accountService.exportTrialBalance(exportTrialBalanceDto);
	}

	public boolean printSearchJournalEntries() {
		AccountService accountService = new AccountService(request, response);

		PrintSearchJournalEntriesDto printSearchJournalEntriesDto = new PrintSearchJournalEntriesDto();
		printSearchJournalEntriesDto.setAccountDetails(request.getParameter("accountidselected"));
		printSearchJournalEntriesDto.setFromDate(request.getParameter("fromdateselected"));
		printSearchJournalEntriesDto.setToDate(request.getParameter("todateselected"));
		printSearchJournalEntriesDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		ResultResponse resultResponse = accountService.printSearchJournalEntries(printSearchJournalEntriesDto);
		if (resultResponse.isSuccess()){
			Map resultMap = resultResponse.getResultMap();
			request.setAttribute("ledgertransactions", resultResponse.getResultMap());
			request.setAttribute("ledgername", resultResponse.getMessage());
			request.setAttribute("fromdateselected", printSearchJournalEntriesDto.getFromDate());
			request.setAttribute("todateselected", printSearchJournalEntriesDto.getToDate());
		}

		return resultResponse.isSuccess();
	}

	public boolean viewVouchersPrint(){
		AccountService accountService = new AccountService(request, response);

		VoucherPrintDto voucherPrintDto = new VoucherPrintDto();
		voucherPrintDto.setFromDate(request.getParameter("fromdateselected"));
		voucherPrintDto.setToDate(request.getParameter("todateselected"));
		voucherPrintDto.setNextVoucher(request.getParameter("voucher"));
		voucherPrintDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		VoucherPrintResponseDto voucherPrintResponseDto = accountService.viewVouchersPrint(voucherPrintDto);
		request.setAttribute("vouchertransactions", voucherPrintResponseDto.getVoucherTransactions());
		request.setAttribute("vouchertype", voucherPrintResponseDto.getVoucherType());
		request.setAttribute("fromdateselected", voucherPrintResponseDto.getFromDateSelected());
		request.setAttribute("todateselected", voucherPrintResponseDto.getToDateSelected());

		return accountService.viewVouchersPrint(voucherPrintDto).isSuccess();
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
		searchLedgerEntriesDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		SearchLedgerEntriesResponseDto searchLedgerEntriesResponseDto = accountService.searchJournalEntries(searchLedgerEntriesDto);
		request.setAttribute("ledgertransactions", searchLedgerEntriesResponseDto.getLedgerTransaction());
		request.setAttribute("ledgername", searchLedgerEntriesResponseDto.getLedgerName());
		request.setAttribute("accountid", searchLedgerEntriesResponseDto.getAccountId());
		request.setAttribute("fromdate", searchLedgerEntriesResponseDto.getFromDate());
		request.setAttribute("todate", searchLedgerEntriesResponseDto.getToDate());

		return searchLedgerEntriesResponseDto.isSuccess();
	}

	public boolean getAllLedgers() {
		AccountService accountService = new AccountService(request, response);
		Integer branchId = Integer.parseInt(httpSession.getAttribute("branchid").toString());
		ResultResponse resultResponse = accountService.getAllLedgers(branchId);
		request.setAttribute("ledgeraccountdetails", resultResponse.getResultList());

		return resultResponse.isSuccess();
	}

	public boolean exportVoucher(){
		AccountService accountService = new AccountService(request, response);

		ExportVoucherDto exportVoucherDto = new ExportVoucherDto();
		exportVoucherDto.setNextVoucher(request.getParameter("voucher"));
		exportVoucherDto.setFromDate(request.getParameter("fromdateselected"));
		exportVoucherDto.setToDate(request.getParameter("todateselected"));
		exportVoucherDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		ResultResponse resultResponse =accountService.exportVoucher(exportVoucherDto);
		request.setAttribute("vouchertype", exportVoucherDto.getNextVoucher());

		return resultResponse.isSuccess();
	}

	public boolean downloadTrialBalance() {
		AccountService accountService = new AccountService(request, response);

		return accountService.downloadTrialBalance();
	}

	public boolean downloadVoucherTransactions() {
		AccountService accountService = new AccountService(request, response);

		return accountService.downloadVoucherTransactions();
	}

	public boolean searchSingleLedgerEntries() {
		AccountService accountService = new AccountService(request, response);

		String accountIds = request.getParameter("accountid");
		String branchId = httpSession.getAttribute("branchid").toString();
		String ledgerName = request.getParameter("ledgername");

		SearchSingleLedgerEntriesResponseDto searchSingleLedgerEntriesResponseDto = accountService.searchSingleLedgerEntries(accountIds, branchId, ledgerName);
		request.setAttribute("ledgertransactions", searchSingleLedgerEntriesResponseDto.getLedgerTransaction());
		request.setAttribute("ledgername", searchSingleLedgerEntriesResponseDto.getLedgerName());
		request.setAttribute("accountid", searchSingleLedgerEntriesResponseDto.getAccountId());
		request.setAttribute("fromdate", searchSingleLedgerEntriesResponseDto.getFromDate());
		request.setAttribute("todate", searchSingleLedgerEntriesResponseDto.getToDate());

		return searchSingleLedgerEntriesResponseDto.isSuccess();
	}

	public boolean viewCancelledVouchers() {
		AccountService accountService = new AccountService(request, response);

		String branchId = httpSession.getAttribute("branchid").toString();

		ResultResponse resultResponse = accountService.viewCancelledVouchers(branchId);
		request.setAttribute("cancelledvouchertransactions", resultResponse.getResultMap());

		return resultResponse.isSuccess();
	}
	public void viewVouchers(int voucherType){
		AccountService accountService = new AccountService(request, response);

		String fromDate = request.getParameter("fromdate");
		String toDate = request.getParameter("todate");
		Integer branchId = Integer.parseInt(httpSession.getAttribute("branchid").toString());
		String nextVoucher = request.getParameter("voucher");

		ViewNextVoucherResponseDto viewNextVoucherResponseDto = accountService.viewVouchers(voucherType, branchId, fromDate, toDate, nextVoucher);
		request.setAttribute("vouchertransactions", viewNextVoucherResponseDto.getVoucherTransactions());
		request.setAttribute("vouchertype", viewNextVoucherResponseDto.getVoucherType());
		request.setAttribute("fromdateselected", viewNextVoucherResponseDto.getFromDateSelected());
		request.setAttribute("todateselected", viewNextVoucherResponseDto.getToDateSelected());


	}

	public boolean balanceSheet() {
		AccountService accountService = new AccountService(request, response);

		String branchId = httpSession.getAttribute("branchid").toString();

		BalanceSheetResponseDto balanceSheetResponseDto = accountService.balanceSheet(branchId);
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

		String branchId = httpSession.getAttribute("branchid").toString();

		CreateVoucherResponseDto createVoucherResponseDto = accountService.createVoucher(branchId);
		request.setAttribute("accountdetailsbalanceexbc", createVoucherResponseDto.getAccountDetailsBalance());
		request.setAttribute("accountdetailsbalanceexpacc", createVoucherResponseDto.getAccountDetailsBalance());
		request.setAttribute("accountdetailsbalancecontra",createVoucherResponseDto.getAccountDetailsBalanceBankCash());
		request.setAttribute("accountdetailsbalancereceipt", createVoucherResponseDto.getAccountDetailsBalanceBankCash());
		request.setAttribute("accountdetailsbalancepayment", createVoucherResponseDto.getAccountDetailsBalanceBankCash());
		request.setAttribute("accountdetailsbalancejournal", createVoucherResponseDto.getAccountDetailsJournalEntry());

		return createVoucherResponseDto.isSuccess();
	}

	public void getSSGroupNames() throws IOException {
		AccountService accountService = new AccountService(request, response);

		String branchId = httpSession.getAttribute("branchid").toString();
		String strAccountSubGroupMasterId = request.getParameter("subgroupname");

		ResultResponse resultResponse = accountService.getSSGroupNames(branchId, strAccountSubGroupMasterId);
		if(resultResponse != null && resultResponse.getResultList() != null){
			request.setAttribute("accountssgroupmaster", resultResponse.getResultList());
		}
	}

	public void getSubGroupNames() throws IOException {
		AccountService accountService = new AccountService(request, response);

		String branchId = httpSession.getAttribute("branchid").toString();
		String strAccountGroupMasterId = request.getParameter("groupname");

		ResultResponse resultResponse = accountService.getSubGroupNames(branchId, strAccountGroupMasterId);
		if(resultResponse != null && resultResponse.getResultList() != null){
			request.setAttribute("accountsubgroupmaster", resultResponse.getResultList());
		}
	}

	public boolean createAccount() {
		AccountService accountService = new AccountService(request, response);

		String branchId = httpSession.getAttribute("branchid").toString();

		CreateAccountResponseDto createAccountResponseDto = accountService.createAccount(branchId);
		request.setAttribute("accountgroupmaster", createAccountResponseDto.getAccountGroupMaster());
		request.setAttribute("accountdetailsbalance", createAccountResponseDto.getAccountDetailsBalance());

		return createAccountResponseDto.isSuccess();
	}
}
