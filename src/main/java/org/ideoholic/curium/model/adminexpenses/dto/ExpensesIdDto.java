package org.ideoholic.curium.model.adminexpenses.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesIdDto {

	private String[] expensesIds;
	private int branchId;
}
