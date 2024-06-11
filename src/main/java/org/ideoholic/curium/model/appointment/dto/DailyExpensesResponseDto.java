package org.ideoholic.curium.model.appointment.dto;

import java.util.List;

import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DailyExpensesResponseDto {
	private String expensesDateBranchName;
	private String branchName;
	private String dayOne;
	private List<Adminexpenses> dailyAdminExpenses;
	private long dailyExpenses;
}
