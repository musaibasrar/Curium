package org.ideoholic.curium.model.stampfees.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otherfee_academicfeesstructure")
public class Academicotherfeesstructure implements java.io.Serializable{


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

	public Academicotherfeesstructure() {
	}

	public Academicotherfeesstructure(int sid) {
		this.sid = sid;
	}

	public Academicotherfeesstructure(int sid, String totalfees, String paidfees,
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