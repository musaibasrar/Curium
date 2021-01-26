package com.model.mess.supplier.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.model.account.dto.Accountdetails;

/**
 * messItems generated by hbm2java
 */
@Entity
@Table(name = "mess_supplierpaymentdetails")
public class MessSuppliersPayment implements java.io.Serializable {

	private Integer id;
	private String externalid;
	private Integer supplierid;
	private String chequeno;
	private Float amount;
	private Integer voucherid;
	private Date issuedate;
	private Date delivereddate;
	private Date cleareddate;
	private String status;
	private Integer branchid;
	private Date entrydate;
	
	public MessSuppliersPayment() {
	}

	public MessSuppliersPayment(String externalid,Integer supplierid,String chequeno,
			Float amount,Integer voucherid,Date issuedate,Date delivereddate,Date cleareddate, Date entrydate, String status,Integer branchid) {
		this.externalid = externalid;
		this.supplierid = supplierid;
		this.chequeno = chequeno;
		this.amount = amount;
		this.voucherid = voucherid;
		this.issuedate = issuedate;
		this.branchid = branchid;
		this.delivereddate = delivereddate;
		this.cleareddate = cleareddate;
		this.entrydate = entrydate;
		this.status = status;
		this.branchid = branchid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExternalid() {
		return externalid;
	}

	public void setExternalid(String externalid) {
		this.externalid = externalid;
	}

	public Integer getSupplierid() {
		return supplierid;
	}

	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}

	public String getChequeno() {
		return chequeno;
	}

	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Integer getVoucherid() {
		return voucherid;
	}

	public void setVoucherid(Integer voucherid) {
		this.voucherid = voucherid;
	}

	public Date getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}

	public Date getDelivereddate() {
		return delivereddate;
	}

	public void setDelivereddate(Date delivereddate) {
		this.delivereddate = delivereddate;
	}

	public Date getCleareddate() {
		return cleareddate;
	}

	public void setCleareddate(Date cleareddate) {
		this.cleareddate = cleareddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBranchid() {
		return branchid;
	}

	public void setBranchid(Integer branchid) {
		this.branchid = branchid;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	
	
}
