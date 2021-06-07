package com.util;

import java.io.Serializable;
import java.util.Date;

public class StockIssuance implements Serializable{
	
	Integer stockid;
	Integer itemid;
	String externalIdStock;
	String externalIdItems;
	String itemname;
	String issuedto;
	String purpose;
	Float itemunitprice;
	Float quantity;
	String unitofmeasure;
	Date transactiondate;
	
	public StockIssuance() {
	}

	public StockIssuance(Integer stockid, Integer itemid, String externalIdStock, String externalIdItems, String itemname, String issuedto, String purpose, Float quantity, Float itemunitprice, String unitofmeasure, Date transactiondate) {
		this.stockid = stockid;
		this.itemid = itemid;
		this.externalIdStock =  externalIdStock;
		this.externalIdItems = externalIdItems;
		this.itemname = itemname;
		this.issuedto = issuedto;
		this.purpose = purpose;
		this.quantity = quantity;
		this.itemunitprice = itemunitprice;
		this.unitofmeasure = unitofmeasure;
		this.transactiondate = transactiondate;
	}

	public Integer getStockid() {
		return stockid;
	}

	public void setStockid(Integer stockid) {
		this.stockid = stockid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	public String getExternalIdStock() {
		return externalIdStock;
	}

	public void setExternalIdStock(String externalIdStock) {
		this.externalIdStock = externalIdStock;
	}

	public String getExternalIdItems() {
		return externalIdItems;
	}

	public void setExternalIdItems(String externalIdItems) {
		this.externalIdItems = externalIdItems;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getIssuedto() {
		return issuedto;
	}

	public void setIssuedto(String issuedto) {
		this.issuedto = issuedto;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Float getItemunitprice() {
		return itemunitprice;
	}

	public void setItemunitprice(Float itemunitprice) {
		this.itemunitprice = itemunitprice;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	public Date getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(Date transactiondate) {
		this.transactiondate = transactiondate;
	}
}
