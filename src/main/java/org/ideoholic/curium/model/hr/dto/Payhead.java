package org.ideoholic.curium.model.hr.dto;

// default package
// Generated 21 Apr, 2018 3:19:13 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Payhead generated by hbm2java
 */
@Entity
@Table(name = "payhead", catalog = "school")
public class Payhead implements java.io.Serializable {

	private Integer idpayhead;
	private String payheadname;
	private String payheadtype;
	private String validatory;
	private String description;
	private String academicyear;
	private int branchid;
	
	public Payhead() {
	}

	public Payhead(String payheadtype, String validatory, String description, String payheadname, String academicyear,
			int branchid) {
		this.payheadtype = payheadtype;
		this.validatory = validatory;
		this.description = description;
		this.payheadname = payheadname;
		this.academicyear = academicyear;
		this.branchid = branchid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idpayhead", unique = true, nullable = false)
	public Integer getIdpayhead() {
		return this.idpayhead;
	}

	public void setIdpayhead(Integer idpayhead) {
		this.idpayhead = idpayhead;
	}

	@Column(name = "payheadtype", length = 40)
	public String getPayheadtype() {
		return this.payheadtype;
	}

	public void setPayheadtype(String payheadtype) {
		this.payheadtype = payheadtype;
	}

	@Column(name = "validatory", length = 45)
	public String getValidatory() {
		return this.validatory;
	}

	public void setValidatory(String validatory) {
		this.validatory = validatory;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "payheadname", length = 200)
	public String getPayheadname() {
		return payheadname;
	}

	public void setPayheadname(String payheadname) {
		this.payheadname = payheadname;
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
