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
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;

	
	@PostMapping("/rejectVoucher")
	public String rejectVoucher() {
		
			new AdminService(request, response).rejectVoucher();
            return viewAllExpenses();
	}

	@PostMapping("/approveVoucher")
	public String approveVoucher() {
		new AdminService(request, response).approveVoucher();
        return viewAllExpenses();
	}

	@PostMapping("/printVoucher")
	public String printVoucher() {
		 new AdminService(request, response).printVoucher();
	        return "paymentvoucherprint";
	}

	@PostMapping("/searchExpenses")
	public String searchExpensesbydate() {
		
		new AdminService(request, response).searchExpensesbydate();
		return "adminexpenses";
	}

	@GetMapping("/viewAllExpenses")
	public String viewAllExpenses() { 
		
		new AdminService(request, response).viewAllExpenses();
		return "adminexpenses";
		
    }

	@PostMapping("/addExpenses")
	public String addExpenses() {
		 if (new AdminService(request, response).addExpenses()) {
	            return viewAllExpenses();
	        } else {
	            return "notSavedExpenses";
	        }
		
	}

	@PostMapping("/deleteMultiple")
	public String deleteMultiple() {
		 new AdminService(request, response).deleteMultiple();
	        return viewAllExpenses();
	}

	

	@PostMapping("/viewExpensesBetweenDates")
	public String viewExpensesBetweenDates() {
		 if (new AdminService(request, response).viewExpensesBetweenDates()) {
	            return "adminexpensesreport";
	        } else {
	            return "notSavedExpenses";
	        }
		
	}

	

	

}
