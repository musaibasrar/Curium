/**
 * 
 */
package com.model.adminexpenses.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.adminexpenses.service.AdminService;
import com.model.feescategory.service.FeesService;
import com.model.student.service.StudentService;

/**
 * @author Musaib_2
 * 
 */
public class AdminAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;

	public AdminAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();
	}

	public String execute(String action, String page) {
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("viewAllExpenses")) {
			url = viewAllExpenses();
		} else if (action.equalsIgnoreCase("addExpenses")) {
			url = addExpenses();
		}else if (action.equalsIgnoreCase("deleteMultiple")) {
			url = deleteMultiple();
		}else if ("searchExpenses".equalsIgnoreCase(action)) {
			url = searchExpensesbydate();
		}else if (action.equalsIgnoreCase("printVoucher")) {
			url = printVoucher();
		}else if (action.equalsIgnoreCase("approveVoucher")) {
			url = approveVoucher();
		}else if (action.equalsIgnoreCase("rejectVoucher")) {
			url = rejectVoucher();
		}
		return url;
	}

	

	

	private String rejectVoucher() {
		
			new AdminService(request, response).rejectVoucher();
            return "Controller?process=AdminProcess&action=viewAllExpenses";
	}

	private String approveVoucher() {
		new AdminService(request, response).approveVoucher();
        return "Controller?process=AdminProcess&action=viewAllExpenses";
	}

	private String printVoucher() {
		 new AdminService(request, response).printVoucher();
	        return "paymentvoucherprint.jsp";
	}

	private String searchExpensesbydate() {
		
		new AdminService(request, response).searchExpensesbydate();
		return "adminexpenses.jsp";
	}

	private String viewAllExpenses() { 
		
		new AdminService(request, response).viewAllExpenses();
		return "adminexpenses.jsp";
		
    }

	private String addExpenses() {
		 if (new AdminService(request, response).addExpenses()) {
	            return "Controller?process=AdminProcess&action=viewAllExpenses";
	        } else {
	            return "notSavedExpenses.jsp";
	        }
		
	}

	private String deleteMultiple() {
		 new AdminService(request, response).deleteMultiple();
	        return "Controller?process=AdminProcess&action=viewAllExpenses";
	}

	

	

	

	

}
