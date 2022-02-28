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
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;
	
	public String ERRORPAGE = "error";

	@PostMapping("/incomeStatement")
	public String incomeStatement() {
		new AccountService(request, response).getIncomeStatement();
		return "incomestatement";
	}

	@PostMapping("/searchLedgerEntries")
	public String searchLedgerEntries() {
		new AccountService(request, response).searchJournalEntries();
		new AccountService(request, response).getAllLedgers();
		return "generalledgerreport";
	}

	@GetMapping("/generalLedgerReport")
	public String generalLedgerReport() {
		new AccountService(request, response).getAllLedgers();
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

		if(new AccountService(request, response).cancelVoucher()){
			return viewVoucherReceipt();
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
		String nextVoucher = DataUtil.emptyString(request.getParameter("voucher"));
		
		if(nextVoucher.equalsIgnoreCase("Receipt")){
			
			if(new AccountService(request, response).viewVouchers(1)){
				return "receiptdetails";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Payment")){
			
			if(new AccountService(request, response).viewVouchers(2)){
				return "paymentdetails";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Contra")){
			
			if(new AccountService(request, response).viewVouchers(3)){
				return "contradetails";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Journal")){
			
			if(new AccountService(request, response).viewVouchers(4)){
				return "journaldetails";
			}
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
		if(new AccountService(request, response).saveJournal()){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveContra")
	public String saveContra() {
		if(new AccountService(request, response).saveContra()){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/savePayment")
	public String savePayment() {
		
		if(new AccountService(request, response).savePayment()){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveReceipt")
	public String saveReceipt() {
		if(new AccountService(request, response).saveReceipt()){
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
		
		if(new AccountService(request, response).deleteAccount()){
			return createAccount();
		}
		return ERRORPAGE;
		
	}

	@PostMapping("/saveAccount")
	public String saveAccount() {
		
		if(!"false".equalsIgnoreCase(new AccountService(request, response).saveAccount())){
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
		
		if(new AccountService(request, response).saveFinancialYear()){
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
    	new AccountService(request, response).exportTrialBalance();
        return "trialbalanceexportsuccess";
	}

	@PostMapping("/printTrialBalance")
	private String printTrialBalance() {
		return "trialbalanceprint";
	}

	@GetMapping("/searchSingleLedgerEntries")
	private String searchSingleLedgerEntries() {
		new AccountService(request, response).searchSingleLedgerEntries();
		new AccountService(request, response).getAllLedgers();
		return "generalledgerreport";
	}
	
	@PostMapping("/printSearchLedgerEntries")
	private String printSearchLedgerEntries() {
		new AccountService(request, response).printSearchJournalEntries();
		return "printgeneralledgerreport";
	}

}
