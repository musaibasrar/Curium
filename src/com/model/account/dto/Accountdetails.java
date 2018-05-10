package com.model.account.dto;

// default package
// Generated 18 Feb, 2018 10:47:34 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Accountdetails generated by hbm2java
 */
@Entity
@Table(name = "accountdetails", catalog = "school")
public class Accountdetails implements java.io.Serializable {

	private Integer accountdetailsid;
	private int accountsubgroupmasterid;
	private int accountgroupid;
	private String accountname;
	private Accountsubgroupmaster accountSubGroupMaster;
	private Accountgroupmaster accountGroupMaster;
	
	public Accountdetails() {
	}

	public Accountdetails(int accountsubgroupmasterid, String accountname, Accountsubgroupmaster accountSubGroupMaster,
			int accountgroupid, Accountgroupmaster accountGroupMaster) {
		this.accountsubgroupmasterid = accountsubgroupmasterid;
		this.accountname = accountname;
		this.accountSubGroupMaster = accountSubGroupMaster;
		this.accountgroupid = accountgroupid;
		this.accountGroupMaster = accountGroupMaster;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "accountdetailsid", unique = true, nullable = false)
	public Integer getAccountdetailsid() {
		return this.accountdetailsid;
	}

	public void setAccountdetailsid(Integer accountdetailsid) {
		this.accountdetailsid = accountdetailsid;
	}

	@Column(name = "accountsubgroupmasterid", nullable = false)
	public int getAccountsubgroupmasterid() {
		return this.accountsubgroupmasterid;
	}

	public void setAccountsubgroupmasterid(int accountsubgroupmasterid) {
		this.accountsubgroupmasterid = accountsubgroupmasterid;
	}

	@Column(name = "accountname", nullable = false, length = 100)
	public String getAccountname() {
		return this.accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public Accountsubgroupmaster getAccountSubGroupMaster() {
		return accountSubGroupMaster;
	}

	public void setAccountSubGroupMaster(Accountsubgroupmaster accountSubGroupMaster) {
		this.accountSubGroupMaster = accountSubGroupMaster;
	}

	public int getAccountgroupid() {
		return accountgroupid;
	}

	public void setAccountgroupid(int accountgroupid) {
		this.accountgroupid = accountgroupid;
	}

	public Accountgroupmaster getAccountGroupMaster() {
		return accountGroupMaster;
	}

	public void setAccountGroupMaster(Accountgroupmaster accountGroupMaster) {
		this.accountGroupMaster = accountGroupMaster;
	}

}
