/**
 * 
 */
package com.model.account.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.academicyear.service.YearService;
import com.model.account.service.AccountService;
import com.model.adminexpenses.service.AdminService;
import com.model.feescategory.service.FeesService;
import com.model.student.service.StudentService;
import com.util.DataUtil;

/**
 * @author Musaib_2
 * 
 */
public class AccountAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	private String ERRORPAGE = "error.jsp";

	public AccountAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action) {

		if ("saveFinancialYear".equalsIgnoreCase(action)) {
			url = saveFinancialYear();
		}else if ("createAccount".equalsIgnoreCase(action)) {
			url = createAccount();
		}else if ("getCurrentFinancialYear".equalsIgnoreCase(action)) {
			url = getCurrentFinancialYear();
		}else if ("getSubGroupNames".equalsIgnoreCase(action)) {
			 getSubGroupNames();
		}else if ("saveAccount".equalsIgnoreCase(action)) {
			url = saveAccount();
		}else if ("deleteAccount".equalsIgnoreCase(action)) {
			url = deleteAccount();
		}else if ("createVoucher".equalsIgnoreCase(action)) {
			url = createVoucher();
		}else if ("saveReceipt".equalsIgnoreCase(action)) {
			url = saveReceipt();
		}else if ("savePayment".equalsIgnoreCase(action)) {
			url = savePayment();
		}else if ("saveContra".equalsIgnoreCase(action)) {
			url = saveContra();
		}else if ("saveJournal".equalsIgnoreCase(action)) {
			url = saveJournal();
		}else if ("balanceSheet".equalsIgnoreCase(action)) {
			url = balanceSheet();
		}else if ("viewVoucherReceipt".equalsIgnoreCase(action)) {
			url = viewVoucherReceipt();
		}else if ("viewNextVoucher".equalsIgnoreCase(action)) {
			url = viewNextVoucher();
		}else if ("trialBalance".equalsIgnoreCase(action)) {
			url = trialBalance();
		}else if ("cancelVoucher".equalsIgnoreCase(action)) {
			url = cancelVoucher();
		}else if ("viewCancelledVouchers".equalsIgnoreCase(action)) {
			url = viewCancelledVouchers();
		}else if ("getSSGroupNames".equalsIgnoreCase(action)) {
				getSSGroupName();
		}else if ("generalLedgerReport".equalsIgnoreCase(action)) {
			url = generalLedgerReport();
		}else if ("searchLedgerEntries".equalsIgnoreCase(action)) {
			url = searchLedgerEntries();
		}else if ("incomeStatement".equalsIgnoreCase(action)) {
			url = incomeStatement();
		}else if ("printSearchLedgerEntries".equalsIgnoreCase(action)) {
			url = printSearchLedgerEntries();
		}
		return url;
		}

	private String printSearchLedgerEntries() {
		new AccountService(request, response).printSearchJournalEntries();
		return "printgeneralledgerreport.jsp";
	}

	private String incomeStatement() {
		new AccountService(request, response).getIncomeStatement();
		return "incomestatement.jsp";
	}

	private String searchLedgerEntries() {
		new AccountService(request, response).searchJournalEntries();
		new AccountService(request, response).getAllLedgers();
		return "generalledgerreport.jsp";
	}

	private String generalLedgerReport() {
		new AccountService(request, response).getAllLedgers();
		return "generalledgerreport.jsp";
}

	private void getSSGroupName() {
		
		try {
			new AccountService(request, response).getSSGroupNames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

	private String viewCancelledVouchers() {
		if(new AccountService(request, response).viewCancelledVouchers()) {
			return "cancelledvoucher.jsp";
		}
		return ERRORPAGE;
	}

	private String cancelVoucher() {

		if(new AccountService(request, response).cancelVoucher()){
			return "Controller?process=AccountProcess&action=viewVoucherReceipt";
		}
			return ERRORPAGE;
		
	}

	private String trialBalance() {
		
		if(new AccountService(request, response).trialBalance()){
			return "trialbalance.jsp";
		}
		return ERRORPAGE;
		
	}

	private String viewNextVoucher() {
		String nextVoucher = DataUtil.emptyString(request.getParameter("voucher"));
		
		if(nextVoucher.equalsIgnoreCase("Receipt")){
			
			if(new AccountService(request, response).viewVouchers(1)){
				return "receiptdetails.jsp";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Payment")){
			
			if(new AccountService(request, response).viewVouchers(2)){
				return "paymentdetails.jsp";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Contra")){
			
			if(new AccountService(request, response).viewVouchers(3)){
				return "contradetails.jsp";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Journal")){
			
			if(new AccountService(request, response).viewVouchers(4)){
				return "journaldetails.jsp";
			}
		}
		return ERRORPAGE;
	}

	private String viewVoucherReceipt() {
		new AccountService(request, response).viewVouchers(1);
			return "receiptdetails.jsp";
	}

	private String balanceSheet() {
		if(new AccountService(request, response).balanceSheet()){
			return "balancesheet.jsp";
		}
		return ERRORPAGE;
		
	}

	private String saveJournal() {
		if(new AccountService(request, response).saveJournal()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}
		return ERRORPAGE;
		
	}

	private String saveContra() {
		if(new AccountService(request, response).saveContra()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}
		return ERRORPAGE;
		
	}

	private String savePayment() {
		
		if(new AccountService(request, response).savePayment()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}
		return ERRORPAGE;
		
	}

	private String saveReceipt() {
		if(new AccountService(request, response).saveReceipt()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}
		return ERRORPAGE;
		
	}

	private String createVoucher() {

		if(new AccountService(request, response).createVoucher()){
			return "createvoucher.jsp";
		}
		return ERRORPAGE;
		
	}

	private String deleteAccount() {
		
		if(new AccountService(request, response).deleteAccount()){
			return "Controller?process=AccountProcess&action=createAccount";
		}
		return ERRORPAGE;
		
	}

	private String saveAccount() {
		
		if(!"false".equalsIgnoreCase(new AccountService(request, response).saveAccount())){
			return "Controller?process=AccountProcess&action=createAccount";
		} 
		return ERRORPAGE;
		
		
	}

	private void getSubGroupNames() {
		
			try {
				new AccountService(request, response).getSubGroupNames();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	private String createAccount() {
		
		if(new AccountService(request, response).createAccount()){
			return "createaccount.jsp";
		}
		return ERRORPAGE;
		
	}

	private String getCurrentFinancialYear() {
		if(new AccountService(request, response).getCurrentFinancialYear()){
			return "financialyear.jsp";
		}
		return ERRORPAGE;
		
	}

	private String saveFinancialYear() { 
		
		if(new AccountService(request, response).saveFinancialYear()){
			return "financialyearsaved.jsp";
		}
		return ERRORPAGE;
		
		
    }

	private String updateYear() {
		 new YearService(request, response).updateYear();
	            return "academicyear.jsp";
	       
		
	}

	

}
