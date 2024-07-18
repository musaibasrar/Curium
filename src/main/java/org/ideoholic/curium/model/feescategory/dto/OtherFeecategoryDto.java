package org.ideoholic.curium.model.feescategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OtherFeecategoryDto {
	
	private String feesCategory;
	private String fromClass;
	private String toClass;
	private String amount;
	private String categoryYearOf;

}
