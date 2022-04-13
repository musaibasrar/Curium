package org.ideoholic.curium.model.feescollection.dto;

import static javax.persistence.GenerationType.IDENTITY;

// default package
// Generated 7 Feb, 2018 12:41:26 AM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Receiptinfo generated by hbm2java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fee_receiptinfo")
public class Receiptinfo implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "receiptnumber", unique = true, nullable = false)
	private Integer receiptnumber;

	@Column(name = "sid", nullable = false)
	private int sid;

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	private Date date;

	@Column(name = "totalamount", precision = 10, scale = 0)
	private Long totalamount;

	@Column(name = "academicyear", length = 15)
	private String academicyear;

	@Column(name = "branchid")
	private int branchid;

	@Column(name = "cancelreceipt")
	private int cancelreceipt;

	@Column(name = "userid")
	private int userid;

	@Column(name = "branchreceiptnumber", length = 20)
	private String branchreceiptnumber;

	@Column(name = "paymenttype", length = 100)
	private String paymenttype;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Receiptinfo")
	@JoinColumn(name = "receiptnumber", nullable = false)
	private Set<Feescollection> feesCollectionRecords = new HashSet<Feescollection>(0);

}