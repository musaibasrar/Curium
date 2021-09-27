package org.ideoholic.curium.model.adminexpenses.dto;

// default package
// Generated 24 Mar, 2015 1:00:53 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Adminexpenses generated by hbm2java
 */
@Entity
@Table(name = "adminexpenses")
public class Adminexpenses implements java.io.Serializable {

	private Integer idAdminExpenses;
	private String itemdescription;
	private String priceofitem;
	private Date entrydate;
	private Integer vno;
	private String paidto;
	private String chequeno;
	private String voucherstatus;
	private int branchid;
	private String paymenttype;
	private String bankname;
	private Date chequedate;

	public Adminexpenses() {
	}

	public Adminexpenses(Date entrydate) {
		this.entrydate = entrydate;
	}

	public Adminexpenses(String itemdescription, String priceofitem,
			Date entrydate, Integer vno, int branchid,String paidto,
			String chequeno,
			String voucherstatus,Date chequedate,String paymenttype,String bankname) {
		this.itemdescription = itemdescription;
		this.priceofitem = priceofitem;
		this.entrydate = entrydate;
		this.vno = vno;
		this.paidto = paidto;
		this.chequeno = chequeno;
		this.voucherstatus = voucherstatus;
		this.branchid = branchid;
		this.chequedate = chequedate;
		this.paymenttype = paymenttype;
		this.bankname = bankname;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idAdminExpenses", unique = true, nullable = false)
	public Integer getIdAdminExpenses() {
		return this.idAdminExpenses;
	}

	public void setIdAdminExpenses(Integer idAdminExpenses) {
		this.idAdminExpenses = idAdminExpenses;
	}

	@Column(name = "item description", length = 500)
	public String getItemdescription() {
		return this.itemdescription;
	}

	public void setItemdescription(String itemdescription) {
		this.itemdescription = itemdescription;
	}

	@Column(name = "priceofitem")
	public String getPriceofitem() {
		return this.priceofitem;
	}

	public void setPriceofitem(String priceofitem) {
		this.priceofitem = priceofitem;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entrydate", nullable = false, length = 19)
	public Date getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	@Column(name = "vno")
	public Integer getVno() {
		return vno;
	}

	public void setVno(Integer vno) {
		this.vno = vno;
	}
	
	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

	public String getPaidto() {
		return paidto;
	}

	public void setPaidto(String paidto) {
		this.paidto = paidto;
	}

	public String getChequeno() {
		return chequeno;
	}

	public void setChequeno(String chequeno) {
		this.chequeno = chequeno;
	}

	public String getVoucherstatus() {
		return voucherstatus;
	}

	public void setVoucherstatus(String voucherstatus) {
		this.voucherstatus = voucherstatus;
	}

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public Date getChequedate() {
		return chequedate;
	}

	public void setChequedate(Date chequedate) {
		this.chequedate = chequedate;
	}
}
