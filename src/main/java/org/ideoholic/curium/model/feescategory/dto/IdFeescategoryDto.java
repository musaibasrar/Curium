package org.ideoholic.curium.model.feescategory.dto;

import org.ideoholic.curium.model.adminexpenses.dto.ExpensesIdDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdFeescategoryDto {

	private String[] idfeescategory;
}
