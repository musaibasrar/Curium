package com.model.hr.dto;

// default package
// Generated 26 Apr, 2018 8:10:08 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.model.employee.dto.Teacher;

/**
 * Leaveapplication generated by hbm2java
 */
@Entity
@Table(name = "hr_leaveapplication")
public class Leaveapplication implements java.io.Serializable {

	private Integer idleaveapplication;
	private Integer idteacher;
	private Date fromdate;
	private Date todate;
	private String leavetype;
	private String reason;
	private String academicyear;
	private String status;
	private Integer totalleaves;
	private Date dateofapply;
	private Date dateofapproval;
	private Teacher teacher;
	private int branchid;
	private int userid;
	
	public Leaveapplication() {
	}

	public Leaveapplication(Integer idteacher, Date fromdate, Date todate,
			String leavetype, String reason, String academicyear, String status, Integer totalleaves, Date dateofapply, Date dateofapproval,
			Teacher teacher, int branchid, int userid) {
		this.idteacher = idteacher;
		this.fromdate = fromdate;
		this.todate = todate;
		this.leavetype = leavetype;
		this.reason = reason;
		this.academicyear = academicyear;
		this.status = status;
		this.totalleaves = totalleaves;
		this.dateofapply = dateofapply;
		this.dateofapproval = dateofapproval;
		this.teacher = teacher;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idleaveapplication", unique = true, nullable = false)
	public Integer getIdleaveapplication() {
		return this.idleaveapplication;
	}

	public void setIdleaveapplication(Integer idleaveapplication) {
		this.idleaveapplication = idleaveapplication;
	}

	@Column(name = "idteacher")
	public Integer getIdteacher() {
		return this.idteacher;
	}

	public void setIdteacher(Integer idteacher) {
		this.idteacher = idteacher;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fromdate", length = 10)
	public Date getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "todate", length = 10)
	public Date getTodate() {
		return this.todate;
	}

	public void setTodate(Date todate) {
		this.todate = todate;
	}

	@Column(name = "leavetype", length = 45)
	public String getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	@Column(name = "reason", length = 500)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	@Column(name = "status", length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "totalleaves")
	public Integer getTotalleaves() {
		return totalleaves;
	}

	public void setTotalleaves(Integer totalleaves) {
		this.totalleaves = totalleaves;
	}

	@Column(name = "dateofapply")
	public Date getDateofapply() {
		return dateofapply;
	}

	public void setDateofapply(Date dateofapply) {
		this.dateofapply = dateofapply;
	}

	@Column(name = "dateofapproval")
	public Date getDateofapproval() {
		return dateofapproval;
	}

	public void setDateofapproval(Date dateofapproval) {
		this.dateofapproval = dateofapproval;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
