package org.ideoholic.curium.model.appointment.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthlyExpensesResponseDto {
	List<String> monthlyExpenses;
	List<String> monthListExpenses;
}
