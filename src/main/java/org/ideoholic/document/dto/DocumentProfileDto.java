package org.ideoholic.document.dto;

import java.util.Date;

public class DocumentProfileDto {
	
	private String branchId;
	private int studentId;
	private String leavingReason;
	private Date dateOfTc;
	private String studentname;
	private String admissionNumber;
	private String addClass;
	private String addSec;
	private byte[] buffer;
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getLeavingReason() {
		return leavingReason;
	}

	public void setLeavingReason(String leavingReason) {
		this.leavingReason = leavingReason;
	}

	public Date getDateOfTc() {
		return dateOfTc;
	}

	public void setDateOfTc(Date dateOfTc) {
		this.dateOfTc = dateOfTc;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
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

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

}
