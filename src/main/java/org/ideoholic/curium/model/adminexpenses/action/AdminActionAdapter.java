package org.ideoholic.curium.model.adminexpenses.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpenseResponseDto;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpensesDateDto;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpensesDto;
import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.ideoholic.curium.model.adminexpenses.dto.ExpensesIdDto;
import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminActionAdapter {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private AdminService adminService;

	public boolean addExpenses() {


		AdminExpensesDto adminexpensesdto = new AdminExpensesDto();
		adminexpensesdto.setItemdescription(request.getParameter("item"));
		adminexpensesdto.setPriceofitem(request.getParameter("price"));
		adminexpensesdto.setPaidto(request.getParameter("paidto"));
		adminexpensesdto.setPaymenttype(request.getParameter("paymenttype"));
		adminexpensesdto.setChequeno(request.getParameter("chequeno"));
		adminexpensesdto.setBankname(request.getParameter("bankname"));
		adminexpensesdto.setChequedate(request.getParameter("chequedate"));
		adminexpensesdto.setEntrydate(request.getParameter("entrydate"));
		adminexpensesdto.setVoucherstatus("pending");
		ResultResponse response = adminService.addExpenses(adminexpensesdto, httpSession.getAttribute("userloginid").toString(), httpSession.getAttribute("branchid").toString());
		if (response == null) {
			return false;
		}

		return response.isSuccess();
	}

	public void rejectVoucher() {

		ExpensesIdDto expenseiddto = new ExpensesIdDto();
		expenseiddto.setExpensesIds(request.getParameterValues("expensesIDs"));
		adminService.rejectVoucher(expenseiddto);
	}

	public void approveVoucher() {

		ExpensesIdDto expenseiddto = new ExpensesIdDto();
		expenseiddto.setExpensesIds(request.getParameterValues("expensesIDs"));
		adminService.approveVoucher(expenseiddto);
		
	}

	public void printVoucher() {

		ExpensesIdDto expenseiddto = new ExpensesIdDto();
		expenseiddto.setExpensesIds(request.getParameterValues("expensesIDs"));

		Adminexpenses adminExpense = adminService.printVoucher(expenseiddto);

		if (adminExpense != null) {
			httpSession.setAttribute("adminexpenses", adminExpense);
		}
	}

	public void searchExpensesbydate() {

		AdminExpensesDto adminexpensesdto = new AdminExpensesDto();
		adminexpensesdto.setSelectedbranchid(request.getParameter("selectedbranchid"));
		adminexpensesdto.setTodate(request.getParameter("todate"));
		adminexpensesdto.setFromdate(request.getParameter("fromdate"));
		adminexpensesdto.setOneday(request.getParameter("oneday"));
		AdminExpenseResponseDto adminExpenseResponseDto = adminService.searchExpensesbydate(adminexpensesdto, httpSession.getAttribute("branchid").toString());

		httpSession.setAttribute("expensesdatebranchname", adminExpenseResponseDto.getExpensesdatebranchname());
		httpSession.setAttribute("branchname", adminExpenseResponseDto.getBranchname());
		request.setAttribute("dayone", adminExpenseResponseDto.getDayone());
		request.setAttribute("datefrom", adminExpenseResponseDto.getDatefrom());
		request.setAttribute("dateto", adminExpenseResponseDto.getDateto());
		request.setAttribute("adminexpenses", adminExpenseResponseDto.getAdminexpenses());
		request.setAttribute("sumofexpenses", adminExpenseResponseDto.getSumofexpenses());
		
	}

	public boolean viewExpensesBetweenDates() {

		AdminExpensesDateDto adminExpensesDateDto = new AdminExpensesDateDto();
		adminExpensesDateDto.setTodate(request.getParameter("todate"));
		adminExpensesDateDto.setFromdate(request.getParameter("fromdate"));
		adminExpensesDateDto.setVoucherstatus(request.getParameter("voucherstatus"));
		adminExpensesDateDto.setPaymenttype(request.getParameter("paymenttype"));
		
		AdminExpenseResponseDto adminExpenseResponseDto = adminService.viewExpensesBetweenDates(adminExpensesDateDto, httpSession.getAttribute("branchid").toString());
		
		httpSession.setAttribute("adminexpenses", adminExpenseResponseDto.getAdminexpenses());
		httpSession.setAttribute("sumofexpenses", adminExpenseResponseDto.getSumofexpenses());
		return adminExpenseResponseDto.isSuccess();
	}
	
	public void deleteMultiple() {

		ExpensesIdDto expenseiddto = new ExpensesIdDto();
		expenseiddto.setExpensesIds(request.getParameterValues("expensesIDs"));
		adminService.deleteMultiple(expenseiddto);
	}
	
	
	public boolean viewAllExpenses() {
		ResultResponse resultResponse = adminService.viewAllExpenses(httpSession.getAttribute("branchid").toString());
		httpSession.setAttribute("adminexpenses", resultResponse.getResultList());
		return resultResponse.isSuccess();
	}


}
