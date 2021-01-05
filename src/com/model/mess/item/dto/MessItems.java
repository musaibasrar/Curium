package com.model.mess.item.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.model.account.dto.Accountdetails;

/**
 * messItems generated by hbm2java
 */
@Entity
@Table(name = "mess_items")
public class MessItems implements java.io.Serializable {

	private Integer id;
	private String externalid;
	private String name;
	private String unitofmeasure;
	private Integer branchid;
	private Integer linkedledgerid;
	private Integer linkedledgeridexpense;
	
	public MessItems() {
	}

	public MessItems(String externalid, String name, String unitofmeasure, Integer branchid, Integer linkedledgerid, Integer linkedledgeridexpense) {
		this.externalid = externalid;
		this.name = name;
		this.unitofmeasure = unitofmeasure;
		this.branchid = branchid;
		this.linkedledgerid = linkedledgerid;
		this.linkedledgeridexpense = linkedledgeridexpense;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitofmeasure() {
		return unitofmeasure;
	}

	public void setUnitofmeasure(String unitofmeasure) {
		this.unitofmeasure = unitofmeasure;
	}

	public Integer getBranchid() {
		return branchid;
	}

	public void setBranchid(Integer branchid) {
		this.branchid = branchid;
	}

	public Integer getLinkedledgerid() {
		return linkedledgerid;
	}

	public void setLinkedledgerid(Integer linkedledgerid) {
		this.linkedledgerid = linkedledgerid;
	}

	public Integer getLinkedledgeridexpense() {
		return linkedledgeridexpense;
	}

	public void setLinkedledgeridexpense(Integer linkedledgeridexpense) {
		this.linkedledgeridexpense = linkedledgeridexpense;
	}

	
}
