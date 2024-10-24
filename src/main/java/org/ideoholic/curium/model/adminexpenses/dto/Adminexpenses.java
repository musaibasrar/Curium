package org.ideoholic.curium.model.adminexpenses.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 24 Mar, 2015 1:00:53 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Adminexpenses generated by hbm2java
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "adminexpenses")
public class Adminexpenses implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idAdminExpenses", unique = true, nullable = false)
	private Integer idAdminExpenses;

	@Column(name = "item description", length = 500)
	private String itemdescription;

	@Column(name = "priceofitem")
	private String priceofitem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entrydate", nullable = false, length = 19)
	private Date entrydate;

	@Column(name = "vno")
	private Integer vno;

	@Column(name = "paidto")
	private String paidto;

	@Column(name = "chequeno")
	private String chequeno;

	@Column(name = "voucherstatus", length = 500)
	private String voucherstatus;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "paymenttype")
	private String paymenttype;

	@Column(name = "bankname")
	private String bankname;

	@Column(name = "chequedate")
	private Date chequedate;

	@Column(name = "userid")
	private int userid;

}
