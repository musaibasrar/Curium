package org.ideoholic.curium.model.feescollection.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "otherfee_receiptinfo")
public class Otherreceiptinfo implements java.io.Serializable {
	private Integer receiptnumber;
	private int sid;
	private Date date;
	private Long totalamount;
	private String academicyear;
	private int branchid;
	private int cancelreceipt;
	private int userid;
	private String branchreceiptnumber;
	private String paymenttype;
	private String classsec;
	private Integer receiptvoucher;
	private Integer journalvoucher;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Receiptinfo")
	private Set<Otherfeescollection> feesCollectionRecords =
			new HashSet<Otherfeescollection>(0);

	public Otherreceiptinfo() {
	}

	public Otherreceiptinfo(int sid) {
		this.sid = sid;
	}

	public Otherreceiptinfo(int sid, Date date, Long totalamount, String academicyear, 
			Set<Otherfeescollection> feesCollecionRecords, int branchid, int cancelreceipt, int userid, String branchreceiptnumber,String paymenttype, String classsec,
			Integer receiptvoucher, Integer journalvoucher) {
		this.sid = sid;
		this.date = date;
		this.totalamount = totalamount;
		this.academicyear = academicyear;
		this.feesCollectionRecords = feesCollecionRecords;
		this.branchid = branchid;
		this.cancelreceipt = cancelreceipt;
		this.userid = userid;
		this.branchreceiptnumber = branchreceiptnumber;
		this.paymenttype = paymenttype;
		this.classsec = classsec;
		this.receiptvoucher = receiptvoucher;
		this.journalvoucher = journalvoucher;
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

	public Set<Otherfeescollection> getFeesCollectionRecords() {
		return feesCollectionRecords;
	}

	public void setFeesCollectionRecords(Set<Otherfeescollection> feesCollectionRecords) {
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getBranchreceiptnumber() {
		return branchreceiptnumber;
	}

	public void setBranchreceiptnumber(String branchreceiptnumber) {
		this.branchreceiptnumber = branchreceiptnumber;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getClasssec() {
		return classsec;
	}

	public void setClasssec(String classsec) {
		this.classsec = classsec;
	}

	public Integer getReceiptvoucher() {
		return receiptvoucher;
	}

	public void setReceiptvoucher(Integer receiptvoucher) {
		this.receiptvoucher = receiptvoucher;
	}

	public Integer getJournalvoucher() {
		return journalvoucher;
	}

	public void setJournalvoucher(Integer journalvoucher) {
		this.journalvoucher = journalvoucher;
	}
}