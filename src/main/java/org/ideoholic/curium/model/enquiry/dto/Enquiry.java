package org.ideoholic.curium.model.enquiry.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "enquiry")
public class Enquiry implements java.io.Serializable{
	private int id;
	private String name;
	private String fathername;
	private String mothername;
	private String admissionclass;
	private String address;
	private String mobileno;
	private String siblings;
	private String academicyear;
	private Date createddate;
	private String status;
	private int branchid;
	private int userid;
	
	
	public Enquiry() {
	}
	public Enquiry(int id, String name, String fathername, String mothername, String admissionclass, Date createddate,
			String address, String mobileno, String siblings, String academicyear, String status,int branchid,int userid) {
		this.id = id;
		this.name = name;
		this.fathername = fathername;
		this.mothername = mothername;
		this.admissionclass = admissionclass;
		this.address = address;
		this.mobileno = mobileno;
		this.siblings = siblings;
		this.academicyear = academicyear;
		this.status= status;
		this.branchid = branchid;
		this.userid = userid;
	}
	
	@Column(name = "id")
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "fathername")
	public String getFathername() {
		return fathername;
	}
	
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	
	@Column(name = "mothername")
	public String getMothername() {
		return mothername;
	}
	
	public void setMothername(String mothername) {
		this.mothername = mothername;
	}
	
	@Column(name = "admissionclass")
	public String getAdmissionclass() {
		return admissionclass;
	}
	
	public void setAdmissionclass(String admissionclass) {
		this.admissionclass = admissionclass;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "mobileno")
	public String getMobileno() {
		return mobileno;
	}
	
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	@Column(name = "siblings")
	public String getSiblings() {
		return siblings;
	}
	
	public void setSiblings(String siblings) {
		this.siblings = siblings;
	}
	
	public String getAcademicyear() {
		return academicyear;
	}
	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}
	
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getBranchid() {
		return branchid;
	}
	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
    
}
