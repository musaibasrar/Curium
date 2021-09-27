package org.ideoholic.curium.model.feescollection.dto;

// default package
// Generated 7 Feb, 2018 12:41:26 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Receiptinfo generated by hbm2java
 */
@Entity
@Table(name = "fee_receiptinfo")
public class Receiptinfo implements java.io.Serializable {

	private Integer receiptnumber;
	private int sid;
	private Date date;
	private Long totalamount;
	private String academicyear;
	private int branchid;
	private int cancelreceipt;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Receiptinfo")
	private Set<Feescollection> feesCollectionRecords =
			new HashSet<Feescollection>(0);
	
	public Receiptinfo() {
	}

	public Receiptinfo(int sid) {
		this.sid = sid;
	}

	public Receiptinfo(int sid, Date date, Long totalamount, String academicyear, 
			Set<Feescollection> feesCollecionRecords, int branchid, int cancelreceipt) {
		this.sid = sid;
		this.date = date;
		this.totalamount = totalamount;
		this.academicyear = academicyear;
		this.feesCollectionRecords = feesCollecionRecords;
		this.branchid = branchid;
		this.cancelreceipt = cancelreceipt;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "receiptnumber", unique = true, nullable = false)
	public Integer getReceiptnumber() {
		return this.receiptnumber;
	}

	public void setReceiptnumber(Integer receiptnumber) {
		this.receiptnumber = receiptnumber;
	}

	@Column(name = "sid", nullable = false)
	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "totalamount", precision = 10, scale = 0)
	public Long getTotalamount() {
		return this.totalamount;
	}

	public void setTotalamount(Long totalamount) {
		this.totalamount = totalamount;
	}

	@Column(name = "academicyear", length = 15)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	public Set<Feescollection> getFeesCollectionRecords() {
		return feesCollectionRecords;
	}

	public void setFeesCollectionRecords(Set<Feescollection> feesCollectionRecords) {
		this.feesCollectionRecords = feesCollectionRecords;
	}

	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

	@Column(name = "cancelreceipt")
	public int getCancelreceipt() {
		return cancelreceipt;
	}

	public void setCancelreceipt(int cancelreceipt) {
		this.cancelreceipt = cancelreceipt;
	}
	
}
