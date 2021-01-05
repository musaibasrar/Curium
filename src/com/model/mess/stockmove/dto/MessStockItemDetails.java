package com.model.mess.stockmove.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.model.mess.item.dto.MessItems;
import com.model.mess.supplier.dto.MessSuppliers;


public class MessStockItemDetails implements java.io.Serializable {

	private Integer stockentryid;
	private String stockentryexternalid;
	private String batchno;
	private Float itemunitprice;
	private Float availablequantity;
	private Integer itemid;
	private String itemname;
	private String unitofmeasure;
	private Integer linkedledgeridassets;
	private Integer linkedledgeridexpenses;
	
	public MessStockItemDetails() {
	}
	
	public MessStockItemDetails(Integer stockentryid, String stockentryexternalid, String batchno, Float availablequantity, Integer itemid, String itemname, String unitofmeasure, Integer linkedledgeridassets, Integer linkedledgeridexpenses) {
		this.stockentryid = stockentryid;
		this.stockentryexternalid = stockentryexternalid;
		this.batchno = batchno;
		this.availablequantity = availablequantity;
		this.itemid = itemid;
		this.itemname = itemname;
		this.unitofmeasure = unitofmeasure;
		this.linkedledgeridassets = linkedledgeridassets;
		this.linkedledgeridexpenses = linkedledgeridexpenses;
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

	
}
