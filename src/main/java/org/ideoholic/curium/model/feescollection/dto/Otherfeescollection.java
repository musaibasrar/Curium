package org.ideoholic.curium.model.feescollection.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "otherfee_feescollection")
public class Otherfeescollection implements java.io.Serializable {

	private Integer feecollectionid;
	private int sfsid;
	private Long amountpaid;
	private int sid;
	private Long fine;
	private Date date;
	private String academicyear;
	private int receiptnumber;
	private int branchid;
	private int userid;

	public Otherfeescollection() {
	}

	public Otherfeescollection(int sfsid, int sid) {
		this.sfsid = sfsid;
		this.sid = sid;
	}

	public Otherfeescollection(int sfsid, Long amountpaid, int sid, Long fine,
			Date date, String academicyear, int receiptnumber, int branchid, int userid) {
		this.sfsid = sfsid;
		this.amountpaid = amountpaid;
		this.sid = sid;
		this.fine = fine;
		this.date = date;
		this.academicyear = academicyear;
		this.receiptnumber = receiptnumber;
		this.branchid = branchid;
		this.userid = userid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "feecollectionid", unique = true, nullable = false)
	public Integer getFeecollectionid() {
		return this.feecollectionid;
	}

	public void setFeecollectionid(Integer feecollectionid) {
		this.feecollectionid = feecollectionid;
	}

	@Column(name = "sfsid", nullable = false)
	public int getSfsid() {
		return this.sfsid;
	}

	public void setSfsid(int sfsid) {
		this.sfsid = sfsid;
	}

	@Column(name = "amountpaid", precision = 10, scale = 0)
	public Long getAmountpaid() {
		return this.amountpaid;
	}

	public void setAmountpaid(Long amountpaid) {
		this.amountpaid = amountpaid;
	}

	@Column(name = "sid", nullable = false)
	public int getSid() {
		return this.sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Column(name = "fine", precision = 10, scale = 0)
	public Long getFine() {
		return this.fine;
	}

	public void setFine(Long fine) {
		this.fine = fine;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "academicyear", length = 45)
	public String getAcademicyear() {
		return this.academicyear;
	}

	public void setAcademicyear(String academicyear) {
		this.academicyear = academicyear;
	}

	@Column(name = "receiptnumber", nullable = false)
	public int getReceiptnumber() {
		return receiptnumber;
	}

	public void setReceiptnumber(int receiptnumber) {
		this.receiptnumber = receiptnumber;
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