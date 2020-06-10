package org.ideoholic.printids.dto;

public class PrintIdsProfileDto {
	
	private String branchId;
    private String studentName;
    private  String addClass;
    private String addSec;
    
    
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

}
