package org.ideoholic.curium.model.studentdiary.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "studentdiary")
public class StudentDiary implements java.io.Serializable {
	
	private int id;
	private int sid;
	private String classsec;
	private String academicyear;
	private int branchid;
	private String subject;
	private String message;
	private Date createddate;
	private int userid;

	public StudentDiary(String classsec, int sid,String academicyear, int branchid, String subject, String message,
			Date startdate, Date enddate, Date createddate, int userid) {
		this.sid = sid;
		this.classsec = classsec;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.subject = subject;
		this.message = message;
		this.createddate = createddate;
		this.userid = userid;
	}

	public StudentDiary() {
		// TODO Auto-generated constructor stub
	}

	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "sid")
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
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

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
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
