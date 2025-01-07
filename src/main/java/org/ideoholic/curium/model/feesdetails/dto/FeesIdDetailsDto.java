package org.ideoholic.curium.model.feesdetails.dto;

import lombok.Data;

@Data
public class FeesIdDetailsDto {

	private String[] feesIds;
	private String toDate;
	private String fromDate;
	private String oneDay;
	private String studentId;
	private String dateoffees;
	private String feesTotalAmount;
	private String grandTotalAmount;
	private String miscellanousamount;
	private String balanceamount;
}
