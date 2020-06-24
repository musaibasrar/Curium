package org.ideoholic.hr.service;

import java.io.IOException;

public interface HrService {

	String leaveType(String branchId);

	String saveLeaveType(String branchId, String leaveTypeName);

	String deleteLeaveType(String idLeave);

	String addLeaves(String branchId, String currentAcademicYear, String[] leaveTypeName1, String[] totalLeaves,
			String[] staff);

	String viewLeavesDetails(String id);

	String leaveDetailsPerYear(String leaveDetailsTeacherSid, String academicYear);

	String payHead(String currentAcademicYear, String branchId);

	String savePayHead(String currentAcademicYear, String payHeadName, String type, String validatory,
			String description, String branchId);

	String addPayHeadStaffDetails(String currentAcademicYear, String[] staff, String[] values, String payHeadId,
			String amountPerc, String branchId);

	String addBasicPay(String currentAcademicYear, String[] staffIds, String[] basicPay, String[] paymentType,
			String[] accountNo, String[] overTime, String branchId);

	String pfSettings(String branchId);

	String addPf(String paidByManagement, String paidByStaff, String datepf, String branchId);

	String deletePf(String[] pfids);

	String saveAdvanceSalaryApproval(String paymentAdvance, String reason, String status, String branchId);

	String deleteAdvaceSalaryApproval(String idPayAdvanceSalary);

	String salaryIssue(String branchId);

	String applyLeave(String currentAcademicYear, String userAuth, String leaveTypeName, String reason, String fromDate,
			String toDate, String userName, String branchId);

	String leaveApprovals(String currentAcademicYear, String branchId);

	String approveLeave(String[] idleaveapplication);

	String rejectLeave(String[] idleaveapplication);

	String processStaffSalary(String[] staffIds, String currentAcademicYear, String month, String year, String branchId,
			String dateprocess);

	String getPayHead(String currentAcademicYear, String payHeadType, String branchId)throws IOException;

	String issueStaffSalary(String currentAcademicYear, String branchId);

	String printSalarySlip(String processSalaryId);

	String getStaffDetails(String currentAcademicYear, String staffId);

	String deletePayHeadStaff(String[] staffIds, String[] idpayheadstaffdetails, String currentAcademicYear);

	String issueProcessedSalary(String[] idProcessSalaryDetails);

	String updateBasicpayEmployees(String branchId, String[] staffIds, String[] basicPay, String[] paymentType,
			String[] accountNo, String[] overTime, String[] academicYear1);

	
}
