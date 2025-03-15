package org.ideoholic.curium.model.mess.item.dto;

import lombok.Data;

@Data
public class PurchaseOrderDto {
	
	private String  supplier;
	private String entryDate;
	private String totalItem;
	private String totalQuantityOrdered;

}
