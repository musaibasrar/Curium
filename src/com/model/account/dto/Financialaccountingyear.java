package com.model.account.dto;

// default package
// Generated 15 Feb, 2018 11:56:25 AM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Financialaccountingyear generated by hbm2java
 */
@Entity
@Table(name = "acc_financialaccountingyear")
public class Financialaccountingyear implements java.io.Serializable {

	private Integer financialid;
	private Date financialstartdate;
	private Date financialenddate;
	private String active;
	private int branchid;
	private int userid;
	
	public Financialaccountingyear() {
	}

	public Financialaccountingyear(Date financialstartdate,
			Date financialenddate, String active, int branchid, int userid) {
		this.financialstartdate = financialstartdate;
		this.financialenddate = financialenddate;
		this.active = active;
		this.branchid  = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "financialid", unique = true, nullable = false)
	public Integer getFinancialid() {
		return this.financialid;
	}

	public void setFinancialid(Integer financialid) {
		this.financialid = financialid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "financialstartdate", nullable = false, length = 10)
	public Date getFinancialstartdate() {
		return this.financialstartdate;
	}

	public void setFinancialstartdate(Date financialstartdate) {
		this.financialstartdate = financialstartdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "financialenddate", nullable = false, length = 10)
	public Date getFinancialenddate() {
		return this.financialenddate;
	}

	public void setFinancialenddate(Date financialenddate) {
		this.financialenddate = financialenddate;
	}

	@Column(name = "active", nullable = false, length = 10)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
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
