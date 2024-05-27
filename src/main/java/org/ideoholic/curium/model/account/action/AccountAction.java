/**
 * 
 */
package org.ideoholic.curium.model.account.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.academicyear.service.YearService;
import org.ideoholic.curium.model.account.service.AccountService;
import org.ideoholic.curium.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Musaib_2
 * 
 */

@Controller
@RequestMapping({"/AccountProcess", "/SubGroupName"})
public class AccountAction {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private AccountActionAdapter accountActionAdapter;
	
	public String ERRORPAGE = "error";

	@PostMapping("/incomeStatement")
	public String incomeStatement() {
		accountActionAdapter.getIncomeStatement();
		return "incomestatement";
	}

	@PostMapping("/searchLedgerEntries")
	public String searchLedgerEntries() {
		accountActionAdapter.searchJournalEntries();
		accountActionAdapter.getAllLedgers();
		return "generalledgerreport";
	}

	@GetMapping("/generalLedgerReport")
	public String generalLedgerReport() {
		accountActionAdapter.getAllLedgers();
		return "generalledgerreport";
}

	@GetMapping("/getSSGroupNames")
	public void getSSGroupName() {
		
		try {
			new AccountService(request, response).getSSGroupNames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

	@GetMapping("/viewCancelledVouchers")
	public String viewCancelledVouchers() {
		if(new AccountService(request, response).viewCancelledVouchers()) {
			return "cancelledvoucher";
		}
		return ERRORPAGE;
	}

	@PostMapping("/cancelVoucher")
	public String cancelVoucher() {

		if(accountActionAdapter.cancelVoucher()){
			return "vouchercancelsuccess";
		}
			return ERRORPAGE;
		
	}

	@RequestMapping( value = "/trialBalance", method = { RequestMethod.GET, RequestMethod.POST } )
	public String trialBalance() {
		
		if(new AccountService(request, response).trialBalance()){
			return "trialbalance";
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/viewNextVoucher")
	public String viewNextVoucher() {
		if (accountActionAdapter.viewVouchers()){
			return "vouchersearch";
		}
		return ERRORPAGE;
	}

	@GetMapping("/viewVoucherReceipt")
	public String viewVoucherReceipt() {
		new AccountService(request, response).viewVouchers(1);
			return "receiptdetails";
	}

	@GetMapping("/balanceSheet")
	public String balanceSheet() {
		if(new AccountService(request, response).balanceSheet()){
			return "balancesheet";
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveJournal")
	public String saveJournal() {
		if(accountActionAdapter.saveJournal()){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveContra")
	public String saveContra() {
		if(accountActionAdapter.saveContra()){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/savePayment")
	public String savePayment() {
		
		if(accountActionAdapter.savePayment()){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveReceipt")
	public String saveReceipt() {
		if(accountActionAdapter.saveReceipt()){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@GetMapping("/createVoucher")
	public String createVoucher() {

		if(new AccountService(request, response).createVoucher()){
			return "createvoucher";
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/deleteAccount")
	public String deleteAccount() {
		
		if(accountActionAdapter.deleteAccount()){
			return createAccount();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveAccount")
	public String saveAccount() {
	    if (accountActionAdapter.saveAccount()) {
		return createAccount();
	    }

	    return ERRORPAGE;
	}

	@GetMapping("/getSubGroupNames")
	public void getSubGroupNames() {
		
			try {
				new AccountService(request, response).getSubGroupNames();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	@GetMapping("/createAccount")
	public String createAccount() {
		
		if(new AccountService(request, response).createAccount()){
			return "createaccount";
		}
		return ERRORPAGE;
		
	}

	@GetMapping("/getCurrentFinancialYear")
	public String getCurrentFinancialYear() {
		if(new AccountService(request, response).getCurrentFinancialYear()){
			return "financialyear";
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveFinancialYear")
	public String saveFinancialYear() { 
		
		if(accountActionAdapter.saveFinancialYear()){
			return "financialyearsaved";
		}
		return ERRORPAGE;
		
		
    }

	public String updateYear() {
		 new YearService(request, response).updateYear();
	            return "academicyear";
	       
		
	}
	
	@PostMapping("/downloadTrialBalance")
	private String downloadTrialBalance() {
		if (new AccountService(request, response).downloadTrialBalance()) {
			return "trialbalanceexportsuccess";
		}
		return "exportfailure";
		}


	@PostMapping("/exportTrialBalance")
	private String exportTrialBalance() {
    	accountActionAdapter.exportTrialBalance();
        return "trialbalanceexportsuccess";
	}

	@PostMapping("/printTrialBalance")
	private String printTrialBalance() {
		return "trialbalanceprint";
	}

	@GetMapping("/searchSingleLedgerEntries")
	private String searchSingleLedgerEntries() {
		new AccountService(request, response).searchSingleLedgerEntries();
		accountActionAdapter.getAllLedgers();
		return "generalledgerreport";
	}
	
	@PostMapping("/printSearchLedgerEntries")
	private String printSearchLedgerEntries() {
		accountActionAdapter.printSearchJournalEntries();
		return "printgeneralledgerreport";
	}

	@PostMapping("/voucherPrint")
	private String voucherPrint() {

		if(accountActionAdapter.viewVouchersPrint()){
			return "printvoucher";
		}
		return ERRORPAGE;
	}
	
	@PostMapping("/exportVoucher")
	private String exportVoucher() {
		
	String nextVoucher = DataUtil.emptyString(request.getParameter("voucher"));
		
		if(nextVoucher.equalsIgnoreCase("Receipt")){
			
			if(new AccountService(request, response).exportVoucher(1)){
				request.setAttribute("vouchertype", nextVoucher);
				return "voucherexportsuccess";
				//receiptdetails
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Payment")){
			
			if(new AccountService(request, response).exportVoucher(2)){
				request.setAttribute("vouchertype", nextVoucher);
				return "voucherexportsuccess";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Contra")){
			
			if(new AccountService(request, response).exportVoucher(3)){
				request.setAttribute("vouchertype", nextVoucher);
				return "voucherexportsuccess";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Journal")){
			
			if(new AccountService(request, response).exportVoucher(4)){
				request.setAttribute("vouchertype", nextVoucher);
				return "voucherexportsuccess";
			}
		}
		return ERRORPAGE;
	}
	
	@PostMapping("/downloadVoucherTransactions")
	private String downloadVoucherTransactions() {
		if (new AccountService(request, response).downloadVoucherTransactions()) {
			return "voucherexportsuccess";
		}
		return "exportfailure";
		}
	
}
