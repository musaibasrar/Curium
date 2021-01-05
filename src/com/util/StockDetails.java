package com.util;

import java.io.Serializable;
import java.util.List;

public class StockDetails implements Serializable{
	
	Integer stockid;
	Integer itemid;
	String externalIdStock;
	String externalIdItems;
	String itemname;
	String unitofmeasure;
	String minquantity;
	String batchno;
	String quantity;
	String itemunitprice;
	String availablequantity;
	
	public StockDetails() {
	}

	public StockDetails(Integer stockid, Integer itemid, String externalIdStock, String externalIdItems, String itemname, String unitofmeasure, String minquantity, String batchno, String quantity, String itemunitprice, String availablequantity) {
		this.stockid = stockid;
		this.itemid = itemid;
		this.externalIdStock =  externalIdStock;
		this.externalIdItems = externalIdItems;
		this.itemname = itemname;
		this.unitofmeasure = unitofmeasure;
		this.minquantity = minquantity;
		this.batchno = batchno;
		this.quantity = quantity;
		this.itemunitprice = itemunitprice;
		this.availablequantity = availablequantity;
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

	public String getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	public String getMinquantity() {
		return minquantity;
	}

	public void setMinquantity(String minquantity) {
		this.minquantity = minquantity;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getItemunitprice() {
		return itemunitprice;
	}

	public void setItemunitprice(String itemunitprice) {
		this.itemunitprice = itemunitprice;
	}

	public String getAvailablequantity() {
		return availablequantity;
	}

	public void setAvailablequantity(String availablequantity) {
		this.availablequantity = availablequantity;
	}
	
	
}
