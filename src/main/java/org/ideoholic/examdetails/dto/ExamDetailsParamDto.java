package org.ideoholic.examdetails.dto;

public class ExamDetailsParamDto {
	private String[] examName;
	private String[] classes;
	private String[] subject;
	private String[] dateOfExam;
	private String[] startTime;
	private String[] endTime;
	private String classAndSec;
	private String admNo;
	private String studentName;
	private String classStudying;
	
	
	public String[] getExamName() {
		return examName;
	}
	public void setExamName(String[] examName) {
		this.examName = examName;
	}
	public String[] getClasses() {
		return classes;
	}
	public void setClasses(String[] classes) {
		this.classes = classes;
	}
	public String[] getSubject() {
		return subject;
	}
	public void setSubject(String[] subject) {
		this.subject = subject;
	}
	public String[] getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(String[] dateOfExam) {
		this.dateOfExam = dateOfExam;
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
	public String getClassAndSec() {
		return classAndSec;
	}
	public void setClassAndSec(String classAndSec) {
		this.classAndSec = classAndSec;
	}
	public String getAdmNo() {
		return admNo;
	}
	public void setAdmNo(String admNo) {
		this.admNo = admNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getClassStudying() {
		return classStudying;
	}
	public void setClassStudying(String classStudying) {
		this.classStudying = classStudying;
	}

}
