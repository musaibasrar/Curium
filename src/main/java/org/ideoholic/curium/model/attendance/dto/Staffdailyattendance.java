package org.ideoholic.curium.model.attendance.dto;

// default package
// Generated 31 Jan, 2018 9:02:38 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Staffdailyattendance generated by hbm2java
 */
@Entity
@Table(name = "att_staffdailyattendance")
public class Staffdailyattendance implements java.io.Serializable {

	private int attendanceid;
	private String attendeeid;
	private String intime;
	private String outtime;
	private Date date;
	private String attendancestatus;
	private String academicyear;
	private int branchid;
	private int userid;
	
	public Staffdailyattendance() {
	}

	public Staffdailyattendance(int attendanceid, String attendeeid, int branchid) {
		this.attendanceid = attendanceid;
		this.attendeeid = attendeeid;
		this.branchid = branchid;
	}

	public Staffdailyattendance(int attendanceid, String attendeeid,
			String intime, String outtime, Date date, String attendancestatus,
			String academicyear, Integer branchid, int userid) {
		this.attendanceid = attendanceid;
		this.attendeeid = attendeeid;
		this.intime = intime;
		this.outtime = outtime;
		this.date = date;
		this.attendancestatus = attendancestatus;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@Column(name = "attendanceid", unique = true, nullable = false)
	public int getAttendanceid() {
		return this.attendanceid;
	}

	public void setAttendanceid(int attendanceid) {
		this.attendanceid = attendanceid;
	}

	@Column(name = "attendeeid", nullable = false, length = 45)
	public String getAttendeeid() {
		return this.attendeeid;
	}

	public void setAttendeeid(String attendeeid) {
		this.attendeeid = attendeeid;
	}

	@Column(name = "intime", length = 15)
	public String getIntime() {
		return this.intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	@Column(name = "outtime", length = 15)
	public String getOuttime() {
		return this.outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "attendancestatus", length = 45)
	public String getAttendancestatus() {
		return this.attendancestatus;
	}

	public void setAttendancestatus(String attendancestatus) {
		this.attendancestatus = attendancestatus;
	}

	@Column(name = "academicyear", length = 10)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	@Column(name = "branchid")
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