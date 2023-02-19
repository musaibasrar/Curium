package org.ideoholic.curium.model.mess.stockmove.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;


public class MessStockItemDetails implements java.io.Serializable {

	private Integer stockentryid;
	private String stockentryexternalid;
	private String batchno;
	private Float itemunitprice;
	private Float availablequantity;
	private Float sgst;
	private Float cgst;
	private Integer itemid;
	private String itemname;
	private String unitofmeasure;
	private Integer linkedledgeridassets;
	private Integer linkedledgeridexpenses;
	private int userid;
	
	public MessStockItemDetails() {
	}
	
	public MessStockItemDetails(Integer stockentryid, String stockentryexternalid, String batchno, Float availablequantity, Integer itemid, String itemname, String unitofmeasure, Integer linkedledgeridassets, Integer linkedledgeridexpenses, int userid, Float sgst, Float cgst) {
		this.stockentryid = stockentryid;
		this.stockentryexternalid = stockentryexternalid;
		this.batchno = batchno;
		this.availablequantity = availablequantity;
		this.cgst = cgst;
		this.sgst = sgst;
		this.itemid = itemid;
		this.itemname = itemname;
		this.unitofmeasure = unitofmeasure;
		this.linkedledgeridassets = linkedledgeridassets;
		this.linkedledgeridexpenses = linkedledgeridexpenses;
		this.userid = userid;
	}

	public Integer getStockentryid() {
		return stockentryid;
	}

	public void setStockentryid(Integer stockentryid) {
		this.stockentryid = stockentryid;
	}

	public String getStockentryexternalid() {
		return stockentryexternalid;
	}

	public void setStockentryexternalid(String stockentryexternalid) {
		this.stockentryexternalid = stockentryexternalid;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public Float getItemunitprice() {
		return itemunitprice;
	}

	public void setItemunitprice(Float itemunitprice) {
		this.itemunitprice = itemunitprice;
	}

	public Float getAvailablequantity() {
		return availablequantity;
	}

	public void setAvailablequantity(Float availablequantity) {
		this.availablequantity = availablequantity;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	public Integer getLinkedledgeridassets() {
		return linkedledgeridassets;
	}

	public void setLinkedledgeridassets(Integer linkedledgeridassets) {
		this.linkedledgeridassets = linkedledgeridassets;
	}

	public Integer getLinkedledgeridexpenses() {
		return linkedledgeridexpenses;
	}

	public void setLinkedledgeridexpenses(Integer linkedledgeridexpenses) {
		this.linkedledgeridexpenses = linkedledgeridexpenses;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Float getSgst() {
		return sgst;
	}

	public void setSgst(Float sgst) {
		this.sgst = sgst;
	}

	public Float getCgst() {
		return cgst;
	}

	public void setCgst(Float cgst) {
		this.cgst = cgst;
	}
	
}
