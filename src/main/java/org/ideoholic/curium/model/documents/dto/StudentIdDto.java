package org.ideoholic.curium.model.documents.dto;

import org.ideoholic.curium.model.adminexpenses.dto.ExpensesIdDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentIdDto {

	private String[] studentIds;
	private int branchId;
	
}
