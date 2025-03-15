package org.ideoholic.curium.model.mess.item.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "mess_stockmoveinfo")
public class MessStockMoveInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer receiptnumber;
	private int sid;
	private Date date;
	private Long totalamount;
	private String academicyear;
	private String studentName;
	private int branchid;
	private int cancelreceipt;
	private int userid;
	private String branchreceiptnumber;
	private String paymenttype;
	private String classsec;
	private Integer receiptvoucher;
	private Integer journalvoucher;
	private Long due;
	private Long misc;
}
