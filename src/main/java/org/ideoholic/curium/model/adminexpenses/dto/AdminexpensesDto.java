package org.ideoholic.curium.model.adminexpenses.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminexpensesDto {

	private Integer idAdminExpenses;
	private String itemdescription;
	private String priceofitem;
	private Date entrydate;
	private Integer vno;
	private String paidto;
	private String chequeno;
	private String voucherstatus;
	private int branchid;
	private String paymenttype;
	private String bankname;
	private Date chequedate;
	private int userid;

}
