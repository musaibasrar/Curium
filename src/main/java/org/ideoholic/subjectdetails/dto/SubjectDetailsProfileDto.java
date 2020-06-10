package org.ideoholic.subjectdetails.dto;

public class SubjectDetailsProfileDto {
	
	private String branchId;
	private String subjectName;
	private String minMarks;
	private String maxMarks;
	private String examName;
	private String examclass;
	private String[] examIds;
	
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getMinMarks() {
		return minMarks;
	}

	public void setMinMarks(String minMarks) {
		this.minMarks = minMarks;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamclass() {
		return examclass;
	}

	public void setExamclass(String examclass) {
		this.examclass = examclass;
	}

	public String[] getExamIds() {
		return examIds;
	}

	public void setExamIds(String[] examIds) {
		this.examIds = examIds;
	}

}
