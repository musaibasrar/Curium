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
@Table(name = "mess_pomaster")
public class PoMaster implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "branchid")
	private Integer branchId;
	
	@Column(name = "externalid",length = 45)
	private String externalId;
	
	@Column(name = "supplierid")
	private Integer supplierId;
	
	@Column(name = "entrydate", length = 10)
	private Date entryDate;
	
	@Column(name = "totalitem")
	private Integer totalItem;
	
	@Column(name = "totalquantityordered")
	private Integer totalQuantityOrdered;
	
	@Column(name = "totalquantityreceived")
	private Integer totalQuantityReceived;

}
