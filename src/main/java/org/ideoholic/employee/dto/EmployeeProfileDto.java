package org.ideoholic.employee.dto;

public class EmployeeProfileDto {
	private String branchId;
	private long id;
	private String id1;
	private String leavingdate;
	private String[] employeeIds;
	private String staffName;
	private String staffDepartment;
	
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getLeavingdate() {
		return leavingdate;
	}

	public void setLeavingdate(String leavingdate) {
		this.leavingdate = leavingdate;
	}

	public String[] getEmployeeIds() {
		return employeeIds;
	}

	public void setEmployeeIds(String[] employeeIds) {
		this.employeeIds = employeeIds;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffDepartment() {
		return staffDepartment;
	}

	public void setStaffDepartment(String staffDepartment) {
		this.staffDepartment = staffDepartment;
	}

}
