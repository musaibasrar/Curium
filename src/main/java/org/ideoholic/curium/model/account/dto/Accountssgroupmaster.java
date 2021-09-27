package org.ideoholic.curium.model.account.dto;

// default package
// Generated 16 Feb, 2018 11:28:08 AM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Accountsubgroupmaster generated by hbm2java
 */
@Entity
@Table(name = "acc_accountssubgroupmaster")
public class Accountssgroupmaster implements java.io.Serializable {

	private Integer ssgroupmasterid;
	private String ssgroupname;
	private Integer subgroupmasterid;
	private Accountsubgroupmaster accountSubGroupMaster;
	private int branchid;
	
	public Accountssgroupmaster() {
	}

	public Accountssgroupmaster(String ssgroupname,
			Integer subgroupmasterid, Accountsubgroupmaster accountSubGroupMaster, int branchid) {
		this.ssgroupname = ssgroupname;
		this.subgroupmasterid = subgroupmasterid;
		this.accountSubGroupMaster = accountSubGroupMaster;
		this.branchid = branchid;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ssgroupmasterid", unique = true, nullable = false)
	public Integer getSsgroupmasterid() {
		return ssgroupmasterid;
	}

	public void setSsgroupmasterid(Integer ssgroupmasterid) {
		this.ssgroupmasterid = ssgroupmasterid;
	}

	@Column(name = "ssgroupname", length = 100)
	public String getSsgroupname() {
		return ssgroupname;
	}

	public void setSsgroupname(String ssgroupname) {
		this.ssgroupname = ssgroupname;
	}

	public Accountsubgroupmaster getAccountSubGroupMaster() {
		return accountSubGroupMaster;
	}

	public void setAccountSubGroupMaster(Accountsubgroupmaster accountSubGroupMaster) {
		this.accountSubGroupMaster = accountSubGroupMaster;
	}

	@Column(name = "branchid")
	public int getBranchid() {
	return branchid;
	}

	public void setBranchid(int branchid) {
	this.branchid = branchid;
	}

	@Column(name = "subgroupmasterid")
	public Integer getSubgroupmasterid() {
		return subgroupmasterid;
	}

	public void setSubgroupmasterid(Integer subgroupmasterid) {
		this.subgroupmasterid = subgroupmasterid;
	}

}
