package org.ideoholic.curium.model.stampfees.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StampFeesDto {
	private String[] studentIds;
	private String feesTotalAmount;
	private String[] feesCategoryIds;
	private String[] feesAmount;
	private String[] concession;
	private String[] totalInstallments;
	private String[] feesYears;
}
