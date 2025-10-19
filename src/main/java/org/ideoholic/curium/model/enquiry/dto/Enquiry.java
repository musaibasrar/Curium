package org.ideoholic.curium.model.enquiry.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "enquiry")
public class Enquiry implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int branchId;
	private int userId;
	private String academicYear;
	private String name;
	private String fathername;
	private String mothername;
	private String admissionclass;
	private String address;
	private String mobileno;
	private String siblingstudy;
	private Date createddate;
	private String status;
	
	public Enquiry() {
	}

	public Enquiry(int id, int branchId, int userId,  String academicYear, String name, String fathername, String mothername,
			String admissionclass, 
			String address, String mobileno, String siblingstudy,
			 Date createddate,String status) {
		this.id = id;
		this.branchId = branchId;
		this.userId = userId;
		this.academicYear = academicYear;
		this.name = name;
		this.fathername = fathername;
		this.mothername = mothername;
		this.admissionclass = admissionclass;
		this.address = address;
		this.mobileno = mobileno;
		this.siblingstudy = siblingstudy;
		this.createddate = createddate;
		this.status = status;
	}
	
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name", length = 45)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "fathername", length = 45)
	public String getFathername() {
		return fathername;
	}
	
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	@Column(name = "mothername", length = 45)
	public String getMothername() {
		return mothername;
	}
	
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	
	@Column(name = "admissionclass", length = 45)
	public String getAdmissionclass() {
		return admissionclass;
	}
	
	public void setAdmissionclass(String admissionclass) {
		this.admissionclass = admissionclass;
	}
	
	
	@Column(name = "address", length = 45)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "mobileno", length = 45)
	public String getMobileno() {
		return mobileno;
	}
	
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	@Column(name = "siblings", length = 45)
	public String getSiblingstudy() {
		return siblingstudy;
	}
	
	public void setSiblingstudy(String siblingstudy) {
		this.siblingstudy = siblingstudy;
	}
	
	
	@Column(name = "created", length = 45)
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	@Column(name = "status", length = 45)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "branchid")
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	@Column(name = "userid")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	
	
    
}
