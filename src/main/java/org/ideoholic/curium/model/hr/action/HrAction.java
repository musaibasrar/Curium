package org.ideoholic.curium.model.hr.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.hr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/HrProcess")
public class HrAction {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	HttpSession httpSession;

	String error = "error";
	
	@PostMapping("/updateBasicPay")
	public String updateBasicPay() {
		new HrService(request, response).updateBasicpayEmployees();
		return "vieweditbasicpay";
	}

	@GetMapping("/viewEditbasicPay")
	public String viewEditbasicPay() {
		new EmployeeService(request, response).basicpayEmployees();
		return "vieweditbasicpay";
	}

	@PostMapping("/cancelStaffSalary")
	public String cancelStaffSalary() {
		
		if(new HrService(request, response).cancelProcessedSalary()){
			return "issuestaffsalary";
		}
		return error;
	}

	@PostMapping("/issueProcessedSalary")
	public String issueProcessedSalary() {
		
		if(new HrService(request, response).issueProcessedSalary()){
			return "issuestaffsalary";
		}
		return error;
	}

	@PostMapping("/deletePayHeadStaff")
	public String deletePayHeadStaff() {
		if(new HrService(request, response).deletePayHeadStaff()){
			return "deletepayhead";
		}
		return "deletepayheadfailed";
	}

	@PostMapping("/getStaffDetails")
	public String getStaffDetails() {
		
		new HrService(request, response).getStaffDetails();
		return "deletepayhead";
	}

	@RequestMapping(value= "/deletePayHead", method= { RequestMethod.GET, RequestMethod.POST })
	public String deletePayHead() {
		new EmployeeService(request, response).ViewAllEmployee();
		return "deletepayhead";
	}
	
	@GetMapping("/printSalarySlip")
	public String printSalarySlip() {
		new HrService(request, response).printSalarySlip();
		return "printsalaryslip";
	}

	@GetMapping("/issueStaffSalary")
	public String issueStaffSalary() {
		
		if(new HrService(request, response).issueStaffSalary()){
			return "issuestaffsalary";
		}
		return error;
	}

	@GetMapping("/getPayHead")
	public String getPayHead() {

			try {
				new HrService(request, response).getPayHead();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "payhead";
		
	}

	@PostMapping("/processStaffSalary")
	public String processStaffSalary() {
		if(new HrService(request, response).processStaffSalary()){
			return "processstaffsalarysuccess";
		}
		return error;
	}

	@PostMapping("/searchEmployeesForProcessSalary")
	public String searchEmployeesForProcessSalary() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "processsalary";
	}

	@GetMapping("/processSalary")
	public String processSalary() {
		new EmployeeService(request, response).viewAllRelations();
		new EmployeeService(request, response).ViewAllEmployee();
		return "processsalary";
	}

	@PostMapping("/rejectLeave")
	public String rejectLeave() {
		
		if(new HrService(request, response).rejectLeave()){
			new HrService(request, response).leaveApprovals();
			return "leaveapprovals";
		}
		return error;
		
	}

	@PostMapping("/approveLeave")
	public String approveLeave() {
		
		if(new HrService(request, response).approveLeave()){
			new HrService(request, response).leaveApprovals();
			return "leaveapprovals";
		}
		return error;
	}

	@GetMapping("/leaveApprovals")
	public String leaveApprovals() {
		if(new HrService(request, response).leaveApprovals()){
			return "leaveapprovals";
		}
		return error;
	}

	@PostMapping("/applyLeave")
	public String applyLeave() {
		if(new HrService(request, response).applyLeave()){
			return "leaveapplicationsuccess";
		}
		return error;
	}

	@GetMapping("/leaveApplication")
	public String leaveApplication() {
		
		new HrService(request, response).leaveType();
		return "leaveapplication";
		
	}

	@GetMapping("/salaryIssue")
	public String salaryIssue() {
		if(new HrService(request, response).salaryIssue()){
			return "salaryissue";	
		}
		return error;
	}

	@PostMapping("/deleteAdvaceSalaryApproval")
	public String deleteAdvaceSalaryApproval() {
		if(new HrService(request, response).deleteAdvaceSalaryApproval()){
			return "advancesalaryapproval";	
		}
		return error;
	}

	@PostMapping("/saveAdvaceSalaryApproval")
	public String saveAdvaceSalaryApproval() {
		if(new HrService(request, response).saveAdvanceSalaryApproval()){
			return "advancesalaryapproval";	
		}
		return error;
	}

	@GetMapping("/salaryApproval")
	public String salaryApproval() {
		new HrService(request, response).salaryApprovalDispaly();
		return "advancesalaryapproval";
	}

	@PostMapping("/saveAdvanceSalary")
	public String saveAdvanceSalary() {
		if(new HrService(request, response).saveAdvanceSalary()){
			return "advancesalarysave";
		}
		return error;
	}

	@GetMapping("/advanceSalary")
	public String advanceSalary() {
		new EmployeeService(request, response).ViewAllEmployee();
		return "advancesalary";
	}

	@PostMapping("/deletePf")
	public String deletePf() {
		new HrService(request, response).deletePf();
		return pfSettings();
	}

	@PostMapping("/addPf")
	public String addPf() {
		new HrService(request, response).addPf();
		return pfSettings();
	}

	@GetMapping("/pfSettings")
	public String pfSettings() {
		new HrService(request, response).pfSettings();
		return "pfsettings";
	}

	@PostMapping("/addBasicPay")
	public String addBasicPay() {
		if(new HrService(request, response).addBasicPay()){
			return "addbasicpaysuccess";
		}
		return error;
	}

	@PostMapping("/searchEmployeesForbasicpay")
	public String searchEmployeesForbasicpay() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "basicpaysettings";
	}

	@GetMapping("/basicPaySettings")
	public String basicPaySettings() {
		new EmployeeService(request, response).ViewAllEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "basicpaysettings";
	}

	@PostMapping("/addPayHeadStaffDetails")
	public String addPayHeadStaffDetails() {
		
		if(new HrService(request, response).addPayHeadStaffDetails()){
			return "addpayheadsuccess";
		}
		return error;
	}

	@PostMapping("/searchEmployeesForPayHead")
	public String searchEmployeesForPayHead() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		return "addpayhead";
	}

	@RequestMapping(value="/addPayHead", method= { RequestMethod.GET, RequestMethod.POST })
	public String addPayHeadStaff() {
		new EmployeeService(request, response).ViewAllEmployee();
		new EmployeeService(request, response).viewAllRelations();
		new HrService(request, response).payHead();
		return "addpayhead";
	}

	@PostMapping("/savePayHead")
	public String savePayHead() {
		if(new HrService(request, response).savePayHead()){
			return payHead();
		}
		return error;
	}

	@GetMapping("/payHead")
	public String payHead() {
		new HrService(request, response).payHead();
		return "payhead";
	}

	@PostMapping("/leaveDetailsPerYear")
	public String leaveDetailsPerYear() {
		
		if(new HrService(request, response).leaveDetailsPerYear()){
			return "leavedetails";
		}
		return error;
	}
	
	@GetMapping("/viewLeavesDetails")
	public String viewLeavesDetails() {
		
		if(new HrService(request, response).viewLeavesDetails()){
			return "leavedetails";
		}
		return error;
	}

	@PostMapping("/addLeaves")
	public String addLeaves() {
		if(new HrService(request, response).addLeaves()){
			return "addleavessuccess";
		}
		return error;
	}

	@GetMapping("/searchEmployees")
	public String searchEmployees() {
		new EmployeeService(request, response).searchEmployee();
		new EmployeeService(request, response).viewAllRelations();
		new HrService(request, response).leaveType();
		return "assignleave";
	}

	@GetMapping("/assignLeave")
	public String assignLeave() {
		new EmployeeService(request, response).viewAllRelations();
		new EmployeeService(request, response).ViewAllEmployee();
		new HrService(request, response).leaveType();
		return "assignleave";
	}

	@PostMapping("/deleteLeaveType")
	public String deleteLeaveType() {
		if(new HrService(request, response).deleteLeaveType()){
			return leaveType();
		}
		return error;
	}

	@PostMapping("/saveLeaveType")
	public String saveLeaveType() {
		
		if(new HrService(request, response).saveLeaveType()){
			return leaveType();
		}
		return error;
	}

	@GetMapping("/leaveType")
	public String leaveType() {
		
		if(new HrService(request, response).leaveType()){
			return "leavetypemaster";
		}
		return error;
	}

	

}
