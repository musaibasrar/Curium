/**
 * 
 */
package org.ideoholic.curium.model.adminexpenses.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Musaib_2
 * 
 */
@Controller
@RequestMapping("/AdminProcess")
public class AdminAction {

	@Autowired
	private AdminActionAdapter adminActionAdapter;

	@PostMapping("/rejectVoucher")
	public String rejectVoucher() {
		
			 adminActionAdapter.rejectVoucher();
            return viewAllExpenses();
	}

	@PostMapping("/approveVoucher")
	public String approveVoucher() {
		 adminActionAdapter.approveVoucher();
        return viewAllExpenses();
	}
	
		@PostMapping("/printVoucher")
	public String printVoucher() {
			adminActionAdapter.printVoucher();
	        return "paymentvoucherprint";
	}
		
		
	
	@PostMapping("/searchExpenses")
	public String searchExpensesbydate() {
		
		adminActionAdapter.searchExpensesbydate();
		return "adminexpenses";
	}
		
	@GetMapping("/viewAllExpenses")
	public String viewAllExpenses() { 
		
		adminActionAdapter.viewAllExpenses();
		return "adminexpenses";
		
    }
	

	@PostMapping("/addExpenses")
	public String addExpenses() {
		 if ( adminActionAdapter.addExpenses()) {
	            return viewAllExpenses();
	        } else {
	            return "notSavedExpenses";
	        }
		
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		adminActionAdapter.deleteMultiple();
	        return viewAllExpenses();
	}

	

	@PostMapping("/viewExpensesBetweenDates")
	public String viewExpensesBetweenDates() {
		 if (adminActionAdapter.viewExpensesBetweenDates()) {
	            return "adminexpensesreport";
	        } else {
	            return "error";
	        }
		
	}

	

	

}
