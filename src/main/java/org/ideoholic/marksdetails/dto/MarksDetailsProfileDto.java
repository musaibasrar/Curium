package org.ideoholic.marksdetails.dto;

public class MarksDetailsProfileDto {
	
	private String branchId;
	private String currentAcademicYear;
	private String[] studentIds;
	private String[] studentsMarks;
	private String exam;
	private String subject;
	private String studentname;
	private String addClass;
	private String addSec;
	private String[] marksid;
	private String[] examClass;
	private byte[] buffer;
	
	
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getCurrentAcademicYear() {
		return currentAcademicYear;
	}
	public void setCurrentAcademicYear(String currentAcademicYear) {
		this.currentAcademicYear = currentAcademicYear;
	}
	public String[] getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(String[] studentIds) {
		this.studentIds = studentIds;
	}
	public String[] getStudentsMarks() {
		return studentsMarks;
	}
	public void setStudentsMarks(String[] studentsMarks) {
		this.studentsMarks = studentsMarks;
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
	public String[] getMarksid() {
		return marksid;
	}
	public void setMarksid(String[] marksid) {
		this.marksid = marksid;
	}
	public String[] getExamClass() {
		return examClass;
	}
	public void setExamClass(String[] examClass) {
		this.examClass = examClass;
	}
	public byte[] getBuffer() {
		return buffer;
	}
	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

}
