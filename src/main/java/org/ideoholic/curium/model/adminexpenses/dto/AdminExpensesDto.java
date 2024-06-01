package org.ideoholic.curium.model.adminexpenses.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminExpensesDto {

	private Integer idAdminExpenses;
	private String itemdescription;
	private String priceofitem;
	private String entrydate;
	private Integer vno;
	private String paidto;
	private String chequeno;
	private String voucherstatus;
	private String paymenttype;
	private String bankname;
	private String chequedate;
	private Integer userid;
	private String selectedbranchid;
	private Integer branchId;
	private String todate;
	private String fromdate;
	private String oneday;
}
