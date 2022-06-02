package com.model.stampfees.dto;

// default package
// Generated 12 May, 2015 11:31:40 AM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Academicfeesstructure generated by hbm2java
 */
@Entity
@Table(name = "fee_academicfeesstructure")
public class Academicfeesstructure implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer feesstructureid;
	private int sid;
	private String totalfees;
	private String paidfees;
	private String academicyear;
	private int branchid;
	private int userid;
	
	public Academicfeesstructure() {
	}

	public Academicfeesstructure(int sid) {
		this.sid = sid;
	}

	public Academicfeesstructure(int sid, String totalfees, String paidfees,
			String academicyear,int branchid, int userid) {
		this.sid = sid;
		this.totalfees = totalfees;
		this.paidfees = paidfees;
		this.academicyear = academicyear;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feesstructureid", unique = true, nullable = false)
	public Integer getFeesstructureid() {
		return this.feesstructureid;
	}

	public void setFeesstructureid(Integer feesstructureid) {
		this.feesstructureid = feesstructureid;
	}

	@Column(name = "sid", nullable = false)
	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Column(name = "totalfees", length = 45)
	public String getTotalfees() {
		return this.totalfees;
	}

	public void setTotalfees(String totalfees) {
		this.totalfees = totalfees;
	}

	@Column(name = "paidfees", length = 45)
	public String getPaidfees() {
		return this.paidfees;
	}

	public void setPaidfees(String paidfees) {
		this.paidfees = paidfees;
	}

	@Column(name = "academicyear", length = 45)
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