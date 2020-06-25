package org.ideoholic.standard.dto;

public class StandardProfileDto {
	
	private String branchId;
	private String classDetails;
	private String section;
	private String[] classIds;
	private String lowerClass;
	private String upperClass;
	private String[] studentIds;
	private String classofStd;
	
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getClassDetails() {
		return classDetails;
	}

	public void setClassDetails(String classDetails) {
		this.classDetails = classDetails;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String[] getClassIds() {
		return classIds;
	}

	public void setClassIds(String[] classIds) {
		this.classIds = classIds;
	}

	public String getLowerClass() {
		return lowerClass;
	}

	public void setLowerClass(String lowerClass) {
		this.lowerClass = lowerClass;
	}

	public String getUpperClass() {
		return upperClass;
	}

	public void setUpperClass(String upperClass) {
		this.upperClass = upperClass;
	}

	public String[] getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(String[] studentIds) {
		this.studentIds = studentIds;
	}

	public String getClassofStd() {
		return classofStd;
	}

	public void setClassofStd(String classofStd) {
		this.classofStd = classofStd;
	}

}
