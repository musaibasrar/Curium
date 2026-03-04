package org.ideoholic.curium.model.mess.stockmove.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill implements java.io.Serializable {
	private static final long serialVersionUID = 7919419435614326406L;

	private Integer id;
	private String itemname;
	private String batchno;
	private Float quantity;
	private String uom;
	private Float salesprice;
	private Float sgst;
	private Float cgst;
	private Float totaltax;
	private Float totalbill;
	private Float totalbillinctax;
	private String totalbillinctaxwords;

}
