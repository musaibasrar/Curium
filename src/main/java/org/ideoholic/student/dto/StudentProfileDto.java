package org.ideoholic.student.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentProfileDto {
	private String branchId;
	private String pageN;
	private long id;
	private String studentIds;
	private String classStudying;
	private String academicYear;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getPageN() {
		return pageN;
	}

	public void setPageN(String pageN) {
		this.pageN = pageN;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	public String getClassStudying() {
		return classStudying;
	}

	public void setClassStudying(String classStudying) {
		this.classStudying = classStudying;
	}

	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

}
