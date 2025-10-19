package org.ideoholic.curium.model.diary.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "diary")
public class Diary implements java.io.Serializable {
	
	private int id;
	private String classsec;
	private String academicyear;
	private String branchid;
	private String subject;
	private String message;
	private Date startdate;
	private Date enddate;
	private Date createddate;
	private int userid;

	public Diary(String classsec, String academicyear, String branchid, String subject, String message,
			Date startdate, Date enddate, Date createddate, int userid) {
		this.classsec = classsec;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.subject = subject;
		this.message = message;
		this.startdate = startdate;
		this.enddate = enddate;
		this.createddate = createddate;
		this.userid = userid;
	}

	public Diary() {
		// TODO Auto-generated constructor stub
	}

	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "classsec", length = 45)
	public String getClasssec() {
		return classsec;
	}

	public void setClasssec(String classsec) {
		this.classsec = classsec;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	@Column(name = "branchid", length = 45)
	public String getBranchid() {
		return branchid;
	}

	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}

	@Column(name = "subject", length = 45)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "message", length = 1000)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "startdate")
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Column(name = "enddate")
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "createddate")
	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	@Column(name = "userid")
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
