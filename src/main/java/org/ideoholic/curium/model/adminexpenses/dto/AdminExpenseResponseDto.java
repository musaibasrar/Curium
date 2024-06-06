package org.ideoholic.curium.model.adminexpenses.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminExpenseResponseDto {

	private String expensesdatebranchname;
	private String branchname;
	private String dayone;
	private String datefrom;
	private String dateto;
	private List<Adminexpenses> adminexpenses;
	private String sumofexpenses;
	private String voucherstatus;
	private String paymenttype;
	private boolean success;
	
}
