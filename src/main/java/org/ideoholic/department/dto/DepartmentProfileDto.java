package org.ideoholic.department.dto;

public class DepartmentProfileDto {
	
	private String branchId;
	private String department1;
	private String[] departmentIds;
	
	
	public String[] getDepartmentIds() {
		return departmentIds;
	}
	public void setDepartmentIds(String[] departmentIds) {
		this.departmentIds = departmentIds;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getDepartment1() {
		return department1;
	}
	public void setDepartment1(String department1) {
		this.department1 = department1;
	}

}
