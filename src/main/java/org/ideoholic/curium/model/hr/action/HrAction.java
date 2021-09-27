package com.model.hr.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.account.service.AccountService;
import com.model.attendance.service.AttendanceService;
import com.model.department.service.DepartmentService;
import com.model.employee.service.EmployeeService;
import com.model.feescategory.service.FeesService;
import com.model.hr.dao.HrDAO;
import com.model.hr.service.HrService;
import com.model.student.service.StudentService;

public class HrAction {

	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession httpSession;
	String url;
	String error = "error.jsp";
	
	public HrAction(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.httpSession = request.getSession();

	}

	public String execute(String action) {
		if ("leaveType".equalsIgnoreCase(action)) {
			url = leaveType();
		}else if ("saveLeaveType".equalsIgnoreCase(action)) {
			url = saveLeaveType();
		}else if ("deleteLeaveType".equalsIgnoreCase(action)) {
			url = deleteLeaveType();
		}else if ("assignLeave".equalsIgnoreCase(action)) {
			url = assignLeave();
		}else if ("searchEmployees".equalsIgnoreCase(action)) {
			url = searchEmployees();
		}else if ("addLeaves".equalsIgnoreCase(action)) {
			url = addLeaves();
		}else if ("viewLeavesDetails".equalsIgnoreCase(action)) {
			url = viewLeavesDetails();
		}else if ("leaveDetailsPerYear".equalsIgnoreCase(action)) {
			url = leaveDetailsPerYear();
		}else if ("payHead".equalsIgnoreCase(action)) {
			url = payHead();
		}else if ("savePayHead".equalsIgnoreCase(action)) {
			url = savePayHead();
		}else if ("addPayHead".equalsIgnoreCase(action)) {
			url = addPayHeadStaff();
		}else if ("searchEmployeesForPayHead".equalsIgnoreCase(action)) {
			url = searchEmployeesForPayHead();
		}else if ("addPayHeadStaffDetails".equalsIgnoreCase(action)) {
			url = addPayHeadStaffDetails();
		}else if ("basicPaySettings".equalsIgnoreCase(action)) {
			url = basicPaySettings();
		}else if ("searchEmployeesForbasicpay".equalsIgnoreCase(action)) {
			url = searchEmployeesForbasicpay();
		}else if ("addBasicPay".equalsIgnoreCase(action)) {
			url = addBasicPay();
		}else if ("pfSettings".equalsIgnoreCase(action)) {
			url = pfSettings();
		}else if ("addPf".equalsIgnoreCase(action)) {
			url = addPf();
		}else if ("deletePf".equalsIgnoreCase(action)) {
			url = deletePf();
		}else if ("advanceSalary".equalsIgnoreCase(action)) {
			url = advanceSalary();
		}else if ("saveAdvanceSalary".equalsIgnoreCase(action)) {
			url = saveAdvanceSalary();
		}else if ("salaryApproval".equalsIgnoreCase(action)) {
			url = salaryApproval();
		}else if ("saveAdvaceSalaryApproval".equalsIgnoreCase(action)) {
			url = saveAdvaceSalaryApproval();
		}else if ("deleteAdvaceSalaryApproval".equalsIgnoreCase(action)) {
			url = deleteAdvaceSalaryApproval();
		}else if ("salaryIssue".equalsIgnoreCase(action)) {
			url = salaryIssue();
		}else if ("leaveApplication".equalsIgnoreCase(action)) {
			url = leaveApplication();
		}else if ("applyLeave".equalsIgnoreCase(action)) {
			url = applyLeave();
		}else if ("leaveApprovals".equalsIgnoreCase(action)) {
			url = leaveApprovals();
		}else if ("approveLeave".equalsIgnoreCase(action)) {
			url = approveLeave();
		}else if ("rejectLeave".equalsIgnoreCase(action)) {
			url = rejectLeave();
		}else if ("processSalary".equalsIgnoreCase(action)) {
			url = processSalary();
		}else if ("searchEmployeesForProcessSalary".equalsIgnoreCase(action)) {
			url = searchEmployeesForProcessSalary();
		}else if ("processStaffSalary".equalsIgnoreCase(action)) {
			url = processStaffSalary();
		}else if ("getPayHead".equalsIgnoreCase(action)) {
			url = getPayHead();
		}else if ("issueStaffSalary".equalsIgnoreCase(action)) {
			url = issueStaffSalary();
		}else if ("printSalarySlip".equalsIgnoreCase(action)) {
			url = printSalarySlip();
		}else if ("deletePayHead".equalsIgnoreCase(action)) {
			url = deletePayHead();
		}else if ("getStaffDetails".equalsIgnoreCase(action)) {
			url = getStaffDetails();
		}else if ("deletePayHeadStaff".equalsIgnoreCase(action)) {
			url = deletePayHeadStaff();
		}else if ("issueProcessedSalary".equalsIgnoreCase(action)) {
			url = issueProcessedSalary();
		}else if ("cancelStaffSalary".equalsIgnoreCase(action)) {
			url = cancelStaffSalary();
		}else if ("viewEditbasicPay".equalsIgnoreCase(action)) {
			url = viewEditbasicPay();
		}else if ("updateBasicPay".equalsIgnoreCase(action)) {
			url = updateBasicPay();
		}
		return url;
	}

	private String updateBasicPay() {
		new HrService(request, response).updateBasicpayEmployees();
		return "vieweditbasicpay.jsp";
	}

	private String viewEditbasicPay() {
		new EmployeeService(request, response).basicpayEmployees();
		return "vieweditbasicpay.jsp";
	}

	private String cancelStaffSalary() {
		
		if(new HrService(request, response).cancelProcessedSalary()){
			return "issuestaffsalary.jsp";
		}
		return error;
	}

	private String issueProcessedSalary() {
		
		if(new HrService(request, response).issueProcessedSalary()){
			return "issuestaffsalary.jsp";
		}
		return error;
	}

	private String deletePayHeadStaff() {
		if(new HrService(request, response).deletePayHeadStaff()){
			return "deletepayhead.jsp";
		}
		return "deletepayheadfailed.jsp";
	}

	private String getStaffDetails() {
		
		new HrService(request, response).getStaffDetails();
		return "deletepayhead.jsp";
	}

	private String deletePayHead() {
		new EmployeeService(request, response).ViewAllEmployee();
		return "deletepayhead.jsp";
	}
	
	
	private String printSalarySlip() {
		new HrService(request, response).printSalarySlip();
		return "printsalaryslip.jsp";
	}

	private String issueStaffSalary() {
		
		if(new HrService(request, response).issueStaffSalary()){
			return "issuestaffsalary.jsp";
		}
		return error;
	}

	private String getPayHead() {

			try {
				new HrService(request, response).getPayHead();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "payhead.jsp";
		
	}

	private String processStaffSalary() {
		if(new HrService(request, response).processStaffSalary()){
			return "processstaffsalarysuccess.jsp";
		}
		return error;
	}

	private String searchEmployeesForProcessSalary() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "processsalary.jsp";
	}

	private String processSalary() {
		new EmployeeService(request, response).viewAllRelations();
		new EmployeeService(request, response).ViewAllEmployee();
		return "processsalary.jsp";
	}

	private String rejectLeave() {
		
		if(new HrService(request, response).rejectLeave()){
			new HrService(request, response).leaveApprovals();
			return "leaveapprovals.jsp";
		}
		return error;
		
	}

	private String approveLeave() {
		
		if(new HrService(request, response).approveLeave()){
			new HrService(request, response).leaveApprovals();
			return "leaveapprovals.jsp";
		}
		return error;
	}

	private String leaveApprovals() {
		if(new HrService(request, response).leaveApprovals()){
			return "leaveapprovals.jsp";
		}
		return error;
	}

	private String applyLeave() {
		if(new HrService(request, response).applyLeave()){
			return "leaveapplicationsuccess.jsp";
		}
		return error;
	}

	private String leaveApplication() {
		
		new HrService(request, response).leaveType();
		return "leaveapplication.jsp";
		
	}

	private String salaryIssue() {
		if(new HrService(request, response).salaryIssue()){
			return "salaryissue.jsp";	
		}
		return error;
	}

	private String deleteAdvaceSalaryApproval() {
		if(new HrService(request, response).deleteAdvaceSalaryApproval()){
			return "advancesalaryapproval.jsp";	
		}
		return error;
	}

	private String saveAdvaceSalaryApproval() {
		if(new HrService(request, response).saveAdvanceSalaryApproval()){
			return "advancesalaryapproval.jsp";	
		}
		return error;
	}

	private String salaryApproval() {
		new HrService(request, response).salaryApprovalDispaly();
		return "advancesalaryapproval.jsp";
	}

	private String saveAdvanceSalary() {
		if(new HrService(request, response).saveAdvanceSalary()){
			return "advancesalarysave.jsp";
		}
		return error;
	}

	private String advanceSalary() {
		new EmployeeService(request, response).ViewAllEmployee();
		return "advancesalary.jsp";
	}

	private String deletePf() {
		new HrService(request, response).deletePf();
		return pfSettings();
	}

	private String addPf() {
		new HrService(request, response).addPf();
		return pfSettings();
	}

	private String pfSettings() {
		new HrService(request, response).pfSettings();
		return "pfsettings.jsp";
	}

	private String addBasicPay() {
		if(new HrService(request, response).addBasicPay()){
			return "addbasicpaysuccess.jsp";
		}
		return error;
	}

	private String searchEmployeesForbasicpay() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "basicpaysettings.jsp";
	}

	private String basicPaySettings() {
		new EmployeeService(request, response).ViewAllEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "basicpaysettings.jsp";
	}

	private String addPayHeadStaffDetails() {
		
		if(new HrService(request, response).addPayHeadStaffDetails()){
			return "addpayheadsuccess.jsp";
		}
		return error;
	}

	private String searchEmployeesForPayHead() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "addpayhead.jsp";
	}

	private String addPayHeadStaff() {
		new EmployeeService(request, response).ViewAllEmployee();
		new EmployeeService(request, response).viewAllRelations();
		new HrService(request, response).payHead();
		return "addpayhead.jsp";
	}

	private String savePayHead() {
		if(new HrService(request, response).savePayHead()){
			return payHead();
		}
		return error;
	}

	private String payHead() {
		new HrService(request, response).payHead();
		return "payhead.jsp";
	}

	private String leaveDetailsPerYear() {
		
		if(new HrService(request, response).leaveDetailsPerYear()){
			return "leavedetails.jsp";
		}
		return error;
	}
	
	private String viewLeavesDetails() {
		
		if(new HrService(request, response).viewLeavesDetails()){
			return "leavedetails.jsp";
		}
		return error;
	}

	private String addLeaves() {
		if(new HrService(request, response).addLeaves()){
			return "addleavessuccess.jsp";
		}
		return error;
	}

	private String searchEmployees() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		new HrService(request, response).leaveType();
		return "assignleave.jsp";
	}

	private String assignLeave() {
		new EmployeeService(request, response).viewAllRelations();
		new EmployeeService(request, response).ViewAllEmployee();
		new HrService(request, response).leaveType();
		return "assignleave.jsp";
	}

	private String deleteLeaveType() {
		if(new HrService(request, response).deleteLeaveType()){
			return "Controller?process=HrProcess&action=leaveType";
		}
		return error;
	}

	private String saveLeaveType() {
		
		if(new HrService(request, response).saveLeaveType()){
			return "Controller?process=HrProcess&action=leaveType";
		}
		return error;
	}

	private String leaveType() {
		
		if(new HrService(request, response).leaveType()){
			return "leavetypemaster.jsp";
		}
		return error;
	}

	

}
