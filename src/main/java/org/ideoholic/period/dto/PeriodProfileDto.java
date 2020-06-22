package org.ideoholic.period.dto;

public class PeriodProfileDto {
	
	private String branchId;
	private String periodMasterid;
	private String[] periodMasterid1;
	private String teacherName;
	
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getPeriodMasterid() {
		return periodMasterid;
	}

	public void setPeriodMasterid(String periodMasterid) {
		this.periodMasterid = periodMasterid;
	}

	public String[] getPeriodMasterid1() {
		return periodMasterid1;
	}

	public void setPeriodMasterid1(String[] periodMasterid1) {
		this.periodMasterid1 = periodMasterid1;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
