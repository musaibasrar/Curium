package org.ideoholic.curium.model.mess.stockmove.dto;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * messItems generated by hbm2java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mess_stockmoves")
public class MessStockMove implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "externalid")
	private String externalid;

	@Column(name = "quantity")
	private Float quantity;

	@Column(name = "purpose")
	private String purpose;

	@Column(name = "transactiondate")
	private Date transactiondate;

	@Column(name = "branchid")
	private Integer branchid;

	@Column(name = "issuedto")
	private String issuedto;

	@Column(name = "itemid")
	private Integer itemid;

	@Column(name = "stockentryid")
	private Integer stockentryid;

	@Column(name = "status")
	private String status;

	@Column(name = "userid")
	private int userid;

}
