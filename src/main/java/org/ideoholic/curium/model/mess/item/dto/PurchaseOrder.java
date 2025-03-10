package org.ideoholic.curium.model.mess.item.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "purchaseorder")
public class PurchaseOrder implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "itemid")
	private int itemId;
	
	@Column(name = "externalid",length = 45)
	private String externalId;
	
	@Column(name = "invoicedetailsid")
	private int invoicedetailsId;
	
	@Column(name = "userid")
	private int userId;
	
	@Column(name = "suppliername", length = 100)
	private String supplierName;
	
	@Column(name = "quantity", length = 45)
	private String quantity;
	
	@Column(name = "receivedquantity", length = 45)
	private String receivedQuantity;
	
	@Column(name = "invoicedate", length = 10)
	private Date invoiceDate;
	
	@Column(name = "uom",length = 45)
	private String uom;
	
	@Column(name = "branchid")
	private int branchId;
}
