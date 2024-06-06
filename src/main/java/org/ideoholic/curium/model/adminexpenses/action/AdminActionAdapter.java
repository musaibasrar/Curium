package org.ideoholic.curium.model.adminexpenses.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.adminexpenses.dto.AdminExpenseResponseDto;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpensesDateDto;
import org.ideoholic.curium.model.adminexpenses.dto.AdminExpensesDto;
import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.ideoholic.curium.model.adminexpenses.dto.ExpensesIdDto;
import org.ideoholic.curium.model.adminexpenses.service.AdminService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminActionAdapter {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private HttpSession httpSession;

	public boolean addExpenses() {
		AdminService adminService = new AdminService(request, response);

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
		adminexpensesdto.setUserid(Integer.parseInt(httpSession.getAttribute("userloginid").toString()));
		adminexpensesdto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
		ResultResponse response = adminService.addExpenses(adminexpensesdto);
		if (response == null) {
			return false;
		}

		return response.isSuccess();
	}

	public void rejectVoucher() {
		AdminService adminService = new AdminService(request, response);
		ExpensesIdDto expenseiddto = new ExpensesIdDto();
		expenseiddto.setExpensesIds(request.getParameterValues("expensesIDs"));
		adminService.rejectVoucher(expenseiddto);
	}

	public void approveVoucher() {
		AdminService adminService = new AdminService(request, response);
		ExpensesIdDto expenseiddto = new ExpensesIdDto();
		expenseiddto.setExpensesIds(request.getParameterValues("expensesIDs"));
		adminService.approveVoucher(expenseiddto);
	}

	public void printVoucher() {
		AdminService adminService = new AdminService(request, response);
		ExpensesIdDto expenseiddto = new ExpensesIdDto();
		expenseiddto.setExpensesIds(request.getParameterValues("expensesIDs"));
		expenseiddto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

		Adminexpenses adminExpense = adminService.printVoucher(expenseiddto);

		if (adminExpense != null) {
			httpSession.setAttribute("adminexpenses", adminExpense);
		}
	}

	public void searchExpensesbydate() {

		AdminService adminService = new AdminService(request, response);
		AdminExpensesDto adminexpensesdto = new AdminExpensesDto();
		adminexpensesdto.setSelectedbranchid(request.getParameter("selectedbranchid"));
		adminexpensesdto.setTodate(request.getParameter("todate"));
		adminexpensesdto.setFromdate(request.getParameter("fromdate"));
		adminexpensesdto.setOneday(request.getParameter("oneday"));
		adminexpensesdto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
		AdminExpenseResponseDto adminExpenseResponseDto = adminService.searchExpensesbydate(adminexpensesdto);

		httpSession.setAttribute("expensesdatebranchname", adminExpenseResponseDto.getExpensesdatebranchname());
		httpSession.setAttribute("branchname", adminExpenseResponseDto.getBranchname());
		request.setAttribute("dayone", adminExpenseResponseDto.getDayone());
		request.setAttribute("datefrom", adminExpenseResponseDto.getDatefrom());
		request.setAttribute("dateto", adminExpenseResponseDto.getDateto());
		request.setAttribute("adminexpenses", adminExpenseResponseDto.getAdminexpenses());
		request.setAttribute("sumofexpenses", adminExpenseResponseDto.getSumofexpenses());
		
	}

	public boolean viewExpensesBetweenDates() {
		AdminService adminService = new AdminService(request, response);
		AdminExpensesDateDto adminExpensesDateDto = new AdminExpensesDateDto();
		adminExpensesDateDto.setTodate(request.getParameter("todate"));
		adminExpensesDateDto.setFromdate(request.getParameter("fromdate"));
		adminExpensesDateDto.setVoucherstatus(request.getParameter("voucherstatus"));
		adminExpensesDateDto.setPaymenttype(request.getParameter("paymenttype"));
		adminExpensesDateDto.setBranchId(httpSession.getAttribute("branchid").toString());
		
		AdminExpenseResponseDto adminExpenseResponseDto = adminService.viewExpensesBetweenDates(adminExpensesDateDto);
		
		httpSession.setAttribute("adminexpenses", adminExpenseResponseDto.getAdminexpenses());
		httpSession.setAttribute("sumofexpenses", adminExpenseResponseDto.getSumofexpenses());
		return adminExpenseResponseDto.isSuccess();
	}

}
