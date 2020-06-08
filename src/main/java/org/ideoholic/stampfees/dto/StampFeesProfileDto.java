package org.ideoholic.stampfees.dto;

public class StampFeesProfileDto {
	
	private String branchId;
    private String studentname;
    private String addClass;
    private String addSec;
    private String currentYear;
    private String[] studentIds;
    
    
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getAddClass() {
		return addClass;
	}
	public void setAddClass(String addClass) {
		this.addClass = addClass;
	}
	public String getAddSec() {
		return addSec;
	}
	public void setAddSec(String addSec) {
		this.addSec = addSec;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public String[] getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(String[] studentIds) {
		this.studentIds = studentIds;
	}

}
