package org.ideoholic.examdetails.dto;

public class ExamDetailsProfileDto {
	
	private String branchId;
	private String examName;
	private String[] examIds;
	private String[] subject;
	private String[] date;
	private String[] startTime;
    private String[] endTime;
	private String[] classesSelected;
	private String academicYear;
	private String exam;
	
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String[] getExamIds() {
		return examIds;
	}
	public void setExamIds(String[] examIds) {
		this.examIds = examIds;
	}
	public String[] getSubject() {
		return subject;
	}
	public void setSubject(String[] subject) {
		this.subject = subject;
	}
	public String[] getDate() {
		return date;
	}
	public void setDate(String[] date) {
		this.date = date;
	}
	public String[] getStartTime() {
		return startTime;
	}
	public void setStartTime(String[] startTime) {
		this.startTime = startTime;
	}
	public String[] getEndTime() {
		return endTime;
	}
	public void setEndTime(String[] endTime) {
		this.endTime = endTime;
	}
	public String[] getClassesSelected() {
		return classesSelected;
	}
	public void setClassesSelected(String[] classesSelected) {
		this.classesSelected = classesSelected;
	}
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}

}
