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
		new HrService(request, response).updateBasicpayEmployees();
		return "vieweditbasicpay";
	}

	@GetMapping("/viewEditbasicPay")
	public String viewEditbasicPay() {
		employeeActionAdapter.basicpayEmployees();
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
		employeeActionAdapter.ViewAllEmployee();
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
		
		hrActionAdapter.leaveType();
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
		employeeActionAdapter.ViewAllEmployee();
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
		
		if(new HrService(request, response).addPayHeadStaffDetails()){
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
