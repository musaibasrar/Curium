package org.ideoholic.curium.model.feesdetails.dto;

import lombok.Data;

@Data
public class FeesIdDetailsDto {

	private String[] feesIds;
	private String toDate;
	private String fromDate;
	private String oneDay;
}
