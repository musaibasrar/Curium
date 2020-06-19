package org.ideoholic.account.service;

import java.io.IOException;

public interface AccountService {

	String saveFinancialYear(String branchId, String fromDate, String toDate, String active);

	String createAccount(String branchId);

	String getCurrentFinancialYear(String branchId);

	String getSubGroupNames(String branchId, int accountGroupMasterId) throws IOException;

	String saveAccount(String branchId, String newSubGroup, String newSSGroup, String subGroupName, String ssGroupName,
			String groupNam, String accountName, String accountCode);

	String deleteAccount(String[] accountIds);

	String createVoucher(String branchId);

	String saveReceipt(String branchId, String draccountName, String craccountName, String receiptVoucher,
			String drAmount, String crAmount, String receiptDate, String receiptNarration);

	String savePayment(String branchId, String draccountNamePayment, String craccountNamePayment, String paymentVoucher,
			String drAmountPayment, String crAmountPayment, String paymentDate, String paymentNarration);

	String saveContra(String branchId, String draccountNameContra, String craccountNameContra, String contraVoucher,
			String drAmountContra, String crAmountContra, String contraDate, String contraNarration);

	String saveJournal(String branchId, String draccountNameJournal, String craccountNameJournal, String contraVoucher,
			String drAmountJournal, String crAmountJournal, String journalDate, String journalNarration);

	String balanceSheet(String branchId);

	String trialBalance(String branchId, String fromDate, String toDate);

	String cancelVoucher(String[] receiptIds, int voucherType);

	String viewCancelledVouchers(String branchId);

	String getSSGroupNames(String branchId, String subGroupName) throws IOException;

}
