package org.ideoholic.curium.model.hr.dto;

// default package
// Generated 24 Apr, 2018 1:06:54 AM by Hibernate Tools 4.0.0

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
 * Pf generated by hbm2java
 */
@Entity
@Table(name = "hr_pf")
public class Pf implements java.io.Serializable {

	private Integer idpf;
	private Integer paidbymanagement;
	private Integer paidbyemployee;
	private Date date;
	private int branchid;


	public Pf() {
	}

	public Pf(Integer paidbymanagement, Integer paidbyemployee, Date date, int branchid) {
		this.paidbymanagement = paidbymanagement;
		this.paidbyemployee = paidbyemployee;
		this.date = date;
		this.branchid = branchid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idpf", unique = true, nullable = false)
	public Integer getIdpf() {
		return this.idpf;
	}

	public void setIdpf(Integer idpf) {
		this.idpf = idpf;
	}

	@Column(name = "paidbymanagement")
	public Integer getPaidbymanagement() {
		return this.paidbymanagement;
	}

	public void setPaidbymanagement(Integer paidbymanagement) {
		this.paidbymanagement = paidbymanagement;
	}

	@Column(name = "paidbyemployee")
	public Integer getPaidbyemployee() {
		return this.paidbyemployee;
	}

	public void setPaidbyemployee(Integer paidbyemployee) {
		this.paidbyemployee = paidbyemployee;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

}
