package org.ideoholic.curium.model.hr.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
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
	@Autowired
	private EmployeeActionAdapter employeeActionAdapter;
	@Autowired
	private HrActionAdapter hrActionAdapter;

	String error = "error";
	
	@PostMapping("/updateBasicPay")
	public String updateBasicPay() {
		hrActionAdapter.updateBasicpayEmployees();
		return "vieweditbasicpay";
	}

	@GetMapping("/viewEditbasicPay")
	public String viewEditbasicPay() {
		employeeActionAdapter.basicpayEmployees();
		return "vieweditbasicpay";
	}

	@PostMapping("/cancelStaffSalary")
	public String cancelStaffSalary() {
		
		if(hrActionAdapter.cancelProcessedSalary()){
			return "issuestaffsalary";
		}
		return error;
	}

	@PostMapping("/issueProcessedSalary")
	public String issueProcessedSalary() {
		
		if(hrActionAdapter.issueProcessedSalary()){
			return "issuestaffsalary";
		}
		return error;
	}

	@PostMapping("/deletePayHeadStaff")
	public String deletePayHeadStaff() {
		if(hrActionAdapter.deletePayHeadStaff()){
			return "deletepayhead";
		}
		return "deletepayheadfailed";
	}

	@PostMapping("/getStaffDetails")
	public String getStaffDetails() {
		
		hrActionAdapter.getStaffDetails();
		return "deletepayhead";
	}

	@RequestMapping(value= "/deletePayHead", method= { RequestMethod.GET, RequestMethod.POST })
	public String deletePayHead() {
		employeeActionAdapter.ViewAllEmployee();
		return "deletepayhead";
	}
	
	@GetMapping("/printSalarySlip")
	public String printSalarySlip() {
		hrActionAdapter.printSalarySlip();
		return "printsalaryslip";
	}

	@GetMapping("/issueStaffSalary")
	public String issueStaffSalary() {
		
		if(hrActionAdapter.issueStaffSalary()){
			return "issuestaffsalary";
		}
		return error;
	}

	@GetMapping("/getPayHead")
	public String getPayHead() {

			try {
				hrActionAdapter.getPayHead();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "payhead";
		
	}

	@PostMapping("/processStaffSalary")
	public String processStaffSalary() {
		if(hrActionAdapter.processStaffSalary()){
			return "processstaffsalarysuccess";
		}
		return error;
	}

	@PostMapping("/searchEmployeesForProcessSalary")
	public String searchEmployeesForProcessSalary() {
		employeeActionAdapter.searchEmployee();
		employeeActionAdapter.viewAllRelations();
		return "processsalary";
	}

	@GetMapping("/processSalary")
	public String processSalary() {
		employeeActionAdapter.viewAllRelations();
		employeeActionAdapter.ViewAllEmployee();
		return "processsalary";
	}

	@PostMapping("/rejectLeave")
	public String rejectLeave() {
		
		if(hrActionAdapter.rejectLeave()){
			hrActionAdapter.leaveApprovals();
			return "leaveapprovals";
		}
		return error;
		
	}

	@PostMapping("/approveLeave")
	public String approveLeave() {
		
		if(hrActionAdapter.approveLeave()){
			hrActionAdapter.leaveApprovals();
			return "leaveapprovals";
		}
		return error;
	}

	@GetMapping("/leaveApprovals")
	public String leaveApprovals() {
		if(hrActionAdapter.leaveApprovals()){
			return "leaveapprovals";
		}
		return error;
	}

	@PostMapping("/applyLeave")
	public String applyLeave() {
		if(hrActionAdapter.applyLeave()){
			return "leaveapplicationsuccess";
		}
		return error;
	}

	@GetMapping("/leaveApplication")
	public String leaveApplication() {
		
		hrActionAdapter.leaveType();
		return "leaveapplication";
		
	}

	@GetMapping("/salaryIssue")
	public String salaryIssue() {
		if(hrActionAdapter.salaryIssue()){
			return "salaryissue";	
		}
		return error;
	}

	@PostMapping("/deleteAdvaceSalaryApproval")
	public String deleteAdvaceSalaryApproval() {
		if(hrActionAdapter.deleteAdvaceSalaryApproval()){
			return "advancesalaryapproval";	
		}
		return error;
	}

	@PostMapping("/saveAdvaceSalaryApproval")
	public String saveAdvaceSalaryApproval() {
		if(hrActionAdapter.saveAdvanceSalaryApproval()){
			return "advancesalaryapproval";	
		}
		return error;
	}

	@GetMapping("/salaryApproval")
	public String salaryApproval() {
		hrActionAdapter.salaryApprovalDispaly();
		return "advancesalaryapproval";
	}

	@PostMapping("/saveAdvanceSalary")
	public String saveAdvanceSalary() {
		if(hrActionAdapter.saveAdvanceSalary()){
			return "advancesalarysave";
		}
		return error;
	}

	@GetMapping("/advanceSalary")
	public String advanceSalary() {
		employeeActionAdapter.ViewAllEmployee();
		return "advancesalary";
	}

	@PostMapping("/deletePf")
	public String deletePf() {
		hrActionAdapter.deletePf();
		return pfSettings();
	}

	@PostMapping("/addPf")
	public String addPf() {
		hrActionAdapter.addPf();
		return pfSettings();
	}

	@GetMapping("/pfSettings")
	public String pfSettings() {
		hrActionAdapter.pfSettings();
		return "pfsettings";
	}

	@PostMapping("/addBasicPay")
	public String addBasicPay() {
		if(hrActionAdapter.addBasicPay()){
			return "addbasicpaysuccess";
		}
		return error;
	}

	@PostMapping("/searchEmployeesForbasicpay")
	public String searchEmployeesForbasicpay() {
		employeeActionAdapter.searchEmployee();
		employeeActionAdapter.viewAllRelations();
		return "basicpaysettings";
	}

	@GetMapping("/basicPaySettings")
	public String basicPaySettings() {
		employeeActionAdapter.ViewAllEmployee();
		employeeActionAdapter.viewAllRelations();
		return "basicpaysettings";
	}

	@PostMapping("/addPayHeadStaffDetails")
	public String addPayHeadStaffDetails() {
		
		if(hrActionAdapter.addPayHeadStaffDetails()){
			return "addpayheadsuccess";
		}
		return error;
	}

	@PostMapping("/searchEmployeesForPayHead")
	public String searchEmployeesForPayHead() {
		employeeActionAdapter.searchEmployee();
		employeeActionAdapter.viewAllRelations();
		return "addpayhead";
	}

	@RequestMapping(value="/addPayHead", method= { RequestMethod.GET, RequestMethod.POST })
	public String addPayHeadStaff() {
		employeeActionAdapter.ViewAllEmployee();
		employeeActionAdapter.viewAllRelations();
		hrActionAdapter.payHead();
		return "addpayhead";
	}

	@PostMapping("/savePayHead")
	public String savePayHead() {
		if(hrActionAdapter.savePayHead()){
			return payHead();
		}
		return error;
	}

	@GetMapping("/payHead")
	public String payHead() {
		hrActionAdapter.payHead();
		return "payhead";
	}

	@PostMapping("/leaveDetailsPerYear")
	public String leaveDetailsPerYear() {
		
		if(hrActionAdapter.leaveDetailsPerYear()){
			return "leavedetails";
		}
		return error;
	}
	
	@GetMapping("/viewLeavesDetails")
	public String viewLeavesDetails() {
		
		if(hrActionAdapter.viewLeavesDetails()){
			return "leavedetails";
		}
		return error;
	}

	@PostMapping("/addLeaves")
	public String addLeaves() {
		if(hrActionAdapter.addLeaves()){
			return "addleavessuccess";
		}
		return error;
	}

	@GetMapping("/searchEmployees")
	public String searchEmployees() {
		employeeActionAdapter.searchEmployee();
		employeeActionAdapter.viewAllRelations();
		hrActionAdapter.leaveType();
		return "assignleave";
	}

	@GetMapping("/assignLeave")
	public String assignLeave() {
		employeeActionAdapter.viewAllRelations();
		employeeActionAdapter.ViewAllEmployee();
		hrActionAdapter.leaveType();
		return "assignleave";
	}

	@PostMapping("/deleteLeaveType")
	public String deleteLeaveType() {
		if(hrActionAdapter.deleteLeaveType()){
			return leaveType();
		}
		return error;
	}

	@PostMapping("/saveLeaveType")
	public String saveLeaveType() {
		
		if(hrActionAdapter.saveLeaveType()){
			return leaveType();
		}
		return error;
	}

	@GetMapping("/leaveType")
	public String leaveType() {
		
		if(hrActionAdapter.leaveType()){
			return "leavetypemaster";
		}
		return error;
	}

}
