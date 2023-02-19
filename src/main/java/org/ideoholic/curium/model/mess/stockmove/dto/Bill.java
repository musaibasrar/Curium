package org.ideoholic.curium.model.mess.stockmove.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.ideoholic.curium.model.mess.supplier.dto.MessSuppliers;


public class Bill implements java.io.Serializable {

	private Integer id;
	private String itemname;
	private String batchno;
	private Float quantity;
	private String uom;
	private Float salesprice;
	private Float sgst;
	private Float cgst;
	private Float totaltax;
	private Float totalbill;
	private Float totalbillinctax;
	private String totalbillinctaxwords;
	
	
	public Bill() {
	}
	
	public Bill(String itemname, String batchno, Float quantity, String uom, Float salesprice, Float sgst, Float cgst, Float totaltax, Float totalbill, Float totalbillinctax
			, String totalbillinctaxwords) {
		this.itemname = itemname;
		this.batchno = batchno;
		this.quantity = quantity;
		this.uom = uom;
		this.salesprice = salesprice;
		this.sgst = sgst;
		this.cgst = cgst;
		this.totaltax = totaltax;
		this.totalbill = totalbill;
		this.totalbillinctax = totalbillinctax;
		this.totalbillinctaxwords = totalbillinctaxwords;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getBatchno() {
		return batchno;
	}

	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Float getSalesprice() {
		return salesprice;
	}

	public void setSalesprice(Float salesprice) {
		this.salesprice = salesprice;
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

	public Float getTotaltax() {
		return totaltax;
	}

	public void setTotaltax(Float totaltax) {
		this.totaltax = totaltax;
	}

	public Float getTotalbill() {
		return totalbill;
	}

	public void setTotalbill(Float totalbill) {
		this.totalbill = totalbill;
	}

	public Float getTotalbillinctax() {
		return totalbillinctax;
	}

	public void setTotalbillinctax(Float totalbillinctax) {
		this.totalbillinctax = totalbillinctax;
	}

	public String getTotalbillinctaxwords() {
		return totalbillinctaxwords;
	}

	public void setTotalbillinctaxwords(String totalbillinctaxwords) {
		this.totalbillinctaxwords = totalbillinctaxwords;
	}

	
}
