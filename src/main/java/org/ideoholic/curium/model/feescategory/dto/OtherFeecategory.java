package org.ideoholic.curium.model.feescategory.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otherfee_feescategory")
public class OtherFeecategory implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idfeescategory;
	private String feescategoryname;
	private Integer amount;
	private String particularname;
	private int branchid;
	private int userid;
	private String academicyear;

	public OtherFeecategory() {
	}

	public OtherFeecategory(String feescategoryname, Integer amount, String particularname,
			int branchid, 	int userid, String academicyear) {
		this.feescategoryname = feescategoryname;
		this.amount = amount;
		this.particularname = particularname;
		this.branchid = branchid;
		this.userid = userid;
		this.academicyear = academicyear;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idfeescategory", unique = true, nullable = false)
	public Integer getIdfeescategory() {
		return this.idfeescategory;
	}

	public void setIdfeescategory(Integer idfeescategory) {
		this.idfeescategory = idfeescategory;
	}

	@Column(name = "feescategory", length = 150)
	public String getFeescategoryname() {
		return this.feescategoryname;
	}

	public void setFeescategoryname(String feescategoryname) {
		this.feescategoryname = feescategoryname;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "particularname", length = 150)
	public String getParticularname() {
		return particularname;
	}

	public void setParticularname(String particularname) {
		this.particularname = particularname;
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

	public String getAcademicyear() {
		return academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

}