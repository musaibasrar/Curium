package org.ideoholic.curium.model.mess.stockmove.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;

/**
 * messItems generated by hbm2java
 */
@Entity
@Table(name = "mess_stockmoves")
public class MessStockMove implements java.io.Serializable {

	private Integer id;
	private String externalid;
	private Float quantity;
	private String purpose;
	private Date transactiondate;
	private Integer branchid;
	private String issuedto;
	private Integer itemid;
	private Integer stockentryid;
	private String status;
	private int userid;
	
	public MessStockMove() {
	}
	
	public MessStockMove(String externalid, Float quantity, String purpose, Date transactiondate, Integer branchid, String issuedto, Integer itemid, Integer stockentryid, String status, int userid) {
		this.externalid = externalid;
		this.quantity = quantity;
		this.purpose = purpose;
		this.transactiondate = transactiondate;
		this.issuedto = issuedto;
		this.branchid = branchid;
		this.itemid = itemid;
		this.stockentryid = stockentryid;
		this.status = status;
		this.userid = userid;
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

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}

	public Integer getBranchid() {
		return branchid;
	}

	public void setBranchid(Integer branchid) {
		this.branchid = branchid;
	}

	public String getIssuedto() {
		return issuedto;
	}

	public void setIssuedto(String issuedto) {
		this.issuedto = issuedto;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public Integer getStockentryid() {
		return stockentryid;
	}

	public void setStockentryid(Integer stockentryid) {
		this.stockentryid = stockentryid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}
