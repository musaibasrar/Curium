package org.ideoholic.curium.model.adminexpenses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminExpensesDateDto {
	
	private String todate;
	private String fromdate;
	private String voucherstatus;
	private String paymenttype;
	private String branchId;

}
