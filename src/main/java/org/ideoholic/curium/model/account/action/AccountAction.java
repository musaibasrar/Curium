/**
 * 
 */
package org.ideoholic.curium.model.account.action;

import java.io.IOException;

import org.ideoholic.curium.model.academicyear.action.YearActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Musaib_2
 * 
 */

@Controller
@RequestMapping({"/AccountProcess", "/SubGroupName"})
public class AccountAction {
	
	@Autowired
	private AccountActionAdapter accountActionAdapter;

	@Autowired
	private YearActionAdapter yearActionAdapter;
	
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
			accountActionAdapter.getSSGroupNames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

	@GetMapping("/viewCancelledVouchers")
	public String viewCancelledVouchers() {
		if(accountActionAdapter.viewCancelledVouchers()) {
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
		
		if(accountActionAdapter.trialBalance()){
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
		accountActionAdapter.viewVouchers(1);
			return "receiptdetails";
	}

	@GetMapping("/balanceSheet")
	public String balanceSheet() {
		if(accountActionAdapter.balanceSheet()){
			return "balancesheet";
		}
		return ERRORPAGE;
		
	}

	@RequestMapping(value = "/saveJournal", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String saveJournal(@RequestParam("listOfFiles") MultipartFile[] uploadedFiles) {
		if(accountActionAdapter.saveJournal(uploadedFiles)){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@RequestMapping(value = "/saveContra", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String saveContra(@RequestParam("listOfFiles") MultipartFile[] uploadedFiles) {
		if(accountActionAdapter.saveContra(uploadedFiles)){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@RequestMapping(value = "/savePayment", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String savePayment(@RequestParam("listOfFiles") MultipartFile[] uploadedFiles) {
		
		if(accountActionAdapter.savePayment(uploadedFiles)){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@RequestMapping(value = "/saveReceipt", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String saveReceipt(@RequestParam("listOfFiles") MultipartFile[] uploadedFiles) {
		if(accountActionAdapter.saveReceipt(uploadedFiles)){
			return createVoucher();
		}
		return ERRORPAGE;
		
	}

	@GetMapping("/createVoucher")
	public String createVoucher() {

		if(accountActionAdapter.createVoucher()){
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
				accountActionAdapter.getSubGroupNames();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	@GetMapping("/createAccount")
	public String createAccount() {
		
		if(accountActionAdapter.createAccount()){
			return "createaccount";
		}
		return ERRORPAGE;
		
	}

	@GetMapping("/getCurrentFinancialYear")
	public String getCurrentFinancialYear() {
		if(accountActionAdapter.getCurrentFinancialYear()){
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

	@GetMapping("/updateYear")
	public String updateYear() {
		 yearActionAdapter.updateYear();
	            return "academicyear";
	       
		
	}
	
	@PostMapping("/downloadTrialBalance")
	private String downloadTrialBalance() {
		if (accountActionAdapter.downloadTrialBalance()) {
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
	
	@PostMapping("/printIncomeStatements")
	private String printIncomeStatements() {
		accountActionAdapter.getIncomeStatement();
		return "incomestatementsprint";
	}

	@GetMapping("/searchSingleLedgerEntries")
	private String searchSingleLedgerEntries() {
		accountActionAdapter.searchSingleLedgerEntries();
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
		if(accountActionAdapter.exportVoucher()){
			return "voucherexportsuccess";
		}
		return ERRORPAGE;
	}
	
	@PostMapping("/downloadVoucherTransactions")
	private String downloadVoucherTransactions() {
		if (accountActionAdapter.downloadVoucherTransactions()) {
			return "voucherexportsuccess";
		}
		return "exportfailure";
		}
	
	@PostMapping("/daybook")
	public String dayBook() {
		accountActionAdapter.getDayBook();
		return "daybook";
	}
}
