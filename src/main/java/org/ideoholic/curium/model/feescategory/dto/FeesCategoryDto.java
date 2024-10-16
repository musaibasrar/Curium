package org.ideoholic.curium.model.feescategory.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeesCategoryDto {
		
		private String[] fromClass;
		private String feesCategory;
		private String amount;
		private String categoryYear;
}
