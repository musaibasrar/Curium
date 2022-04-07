package com.model.hr.dto;

// default package
// Generated 19 Apr, 2018 11:15:02 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.model.employee.dto.Teacher;

/**
 * Leavedetails generated by hbm2java
 */
@Entity
@Table(name = "hr_leavedetails")
public class Leavedetails implements java.io.Serializable {

	private Integer idleavedetails;
	private Integer idleavetypemaster;
	private Integer idteacher;
	private Integer numberofleaves;
	private Leavetypemaster leaveTypeMaster;
	private Teacher teacher;
	private String academicyear;
	private int branchid;
	
	public Leavedetails() {
	}

	public Leavedetails(Integer idleavetypemaster, Integer idteacher,
			Integer numberofleaves, Leavetypemaster leaveTypeMaster, Teacher teacher, String academicyear,int branchid) {
		this.idleavetypemaster = idleavetypemaster;
		this.idteacher = idteacher;
		this.numberofleaves = numberofleaves;
		this.leaveTypeMaster = leaveTypeMaster;
		this.teacher = teacher;
		this.academicyear = academicyear;
		this.branchid = branchid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idleavedetails", unique = true, nullable = false)
	public Integer getIdleavedetails() {
		return this.idleavedetails;
	}

	public void setIdleavedetails(Integer idleavedetails) {
		this.idleavedetails = idleavedetails;
	}

	@Column(name = "idleavetypemaster")
	public Integer getIdleavetypemaster() {
		return this.idleavetypemaster;
	}

	public void setIdleavetypemaster(Integer idleavetypemaster) {
		this.idleavetypemaster = idleavetypemaster;
	}

	@Column(name = "idteacher")
	public Integer getIdteacher() {
		return this.idteacher;
	}

	public void setIdteacher(Integer idteacher) {
		this.idteacher = idteacher;
	}

	@Column(name = "numberofleaves")
	public Integer getNumberofleaves() {
		return this.numberofleaves;
	}

	public void setNumberofleaves(Integer numberofleaves) {
		this.numberofleaves = numberofleaves;
	}

	public Leavetypemaster getLeaveTypeMaster() {
		return leaveTypeMaster;
	}

	public void setLeaveTypeMaster(Leavetypemaster leaveTypeMaster) {
		this.leaveTypeMaster = leaveTypeMaster;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicyear() {
		return academicyear;
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
}