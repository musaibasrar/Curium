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
		// TODO Auto-generated method stub
		if (action.equalsIgnoreCase("saveFinancialYear")) {
			url = saveFinancialYear();
		}else if (action.equalsIgnoreCase("createAccount")) {
			url = createAccount();
		}else if (action.equalsIgnoreCase("getCurrentFinancialYear")) {
			url = getCurrentFinancialYear();
		}else if (action.equalsIgnoreCase("getSubGroupNames")) {
			 getSubGroupNames();
		}else if (action.equalsIgnoreCase("saveAccount")) {
			url = saveAccount();
		}else if (action.equalsIgnoreCase("deleteAccount")) {
			url = deleteAccount();
		}else if (action.equalsIgnoreCase("createVoucher")) {
			url = createVoucher();
		}else if (action.equalsIgnoreCase("saveReceipt")) {
			url = saveReceipt();
		}else if (action.equalsIgnoreCase("savePayment")) {
			url = savePayment();
		}else if (action.equalsIgnoreCase("saveContra")) {
			url = saveContra();
		}else if (action.equalsIgnoreCase("saveJournal")) {
			url = saveJournal();
		}else if (action.equalsIgnoreCase("balanceSheet")) {
			url = balanceSheet();
		}else if (action.equalsIgnoreCase("viewVoucherReceipt")) {
			url = viewVoucherReceipt();
		}else if (action.equalsIgnoreCase("viewNextVoucher")) {
			url = viewNextVoucher();
		}else if (action.equalsIgnoreCase("trialBalance")) {
			url = trailBalance();
		}
		return url;
	}

	private String trailBalance() {
		
		if(new AccountService(request, response).trailBalance()){
			return "trialbalance.jsp";
		}else{
		return ERRORPAGE;
		}
	}

	private String viewNextVoucher() {
		String nextVoucher = DataUtil.emptyString(request.getParameter("voucher"));
		
		if(nextVoucher.equalsIgnoreCase("Receipt")){
			
			if(new AccountService(request, response).viewVoucherReceipt()){
				return "receiptdetails.jsp";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Payment")){
			
			if(new AccountService(request, response).viewVoucherPayment()){
				return "paymentdetails.jsp";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Contra")){
			
			if(new AccountService(request, response).viewVoucherContra()){
				return "contradetails.jsp";
			}
			
		}else if(nextVoucher.equalsIgnoreCase("Journal")){
			
			if(new AccountService(request, response).viewVoucherJournal()){
				return "journaldetails.jsp";
			}
		}
		return ERRORPAGE;
	}

	private String viewVoucherReceipt() {
		
		if(new AccountService(request, response).viewVoucherReceipt()){
			return "receiptdetails.jsp";
		}else{
		return ERRORPAGE;
		}
	}

	private String balanceSheet() {
		if(new AccountService(request, response).balanceSheet()){
			return "balancesheet.jsp";
		}else{
		return ERRORPAGE;
		}
	}

	private String saveJournal() {
		if(new AccountService(request, response).saveJournal()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}else{
		return ERRORPAGE;
		}
	}

	private String saveContra() {
		if(new AccountService(request, response).saveContra()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}else{
		return ERRORPAGE;
		}
	}

	private String savePayment() {
		
		if(new AccountService(request, response).savePayment()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}else{
		return ERRORPAGE;
		}
	}

	private String saveReceipt() {
		if(new AccountService(request, response).saveReceipt()){
			return "Controller?process=AccountProcess&action=createVoucher";
		}else{
		return ERRORPAGE;
		}
	}

	private String createVoucher() {

		if(new AccountService(request, response).createVoucher()){
			return "createvoucher.jsp";
		}else{
		return ERRORPAGE;
		}
	}

	private String deleteAccount() {
		
		if(new AccountService(request, response).deleteAccount()){
			return "Controller?process=AccountProcess&action=createAccount";
		}else{
		return ERRORPAGE;
		}
	}

	private String saveAccount() {
		
		if(new AccountService(request, response).saveAccount()){
			return "Controller?process=AccountProcess&action=createAccount";
		}else{
		return ERRORPAGE;
		}
		
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
		}else{
		return ERRORPAGE;
		}
	}

	private String getCurrentFinancialYear() {
		if(new AccountService(request, response).getCurrentFinancialYear()){
			return "financialyear.jsp";
		}else{
		return ERRORPAGE;
		}
	}

	private String saveFinancialYear() { 
		
		if(new AccountService(request, response).saveFinancialYear()){
			return "financialyearsaved.jsp";
		}else{
		return ERRORPAGE;
		}
		
    }

	private String updateYear() {
		 new YearService(request, response).updateYear();
	            return "academicyear.jsp";
	       
		
	}

	

}
