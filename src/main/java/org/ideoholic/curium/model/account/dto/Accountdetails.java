package org.ideoholic.curium.model.account.dto;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Accountdetails generated by hbm2java
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acc_accountdetails")
public class Accountdetails implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "accountdetailsid", unique = true, nullable = false)
	private Integer accountdetailsid;

	@Column(name = "accountsubgroupmasterid", nullable = false)
	private int accountsubgroupmasterid;

	@Column(name = "ssgroupmasterid", nullable = false)
	private int ssgroupmasterid;

	@Column(name = "accountgroupid")
	private int accountgroupid;

	@Column(name = "accountname", nullable = false, length = 100)
	private String accountname;

	@Column(name = "accountcode", length = 100, nullable = false)
	private String accountcode;

	@ManyToOne
	@JoinColumn(name = "ssgroupmasterid")
	private Accountssgroupmaster accountSSGroupMaster;

	@ManyToOne
	@JoinColumn(name = "accountsubgroupmasterid")
	private Accountsubgroupmaster accountSubGroupMaster;

	@ManyToOne
	@JoinColumn(name = "accountgroupid")
	private Accountgroupmaster accountGroupMaster;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "userid")
	private int userid;

}
