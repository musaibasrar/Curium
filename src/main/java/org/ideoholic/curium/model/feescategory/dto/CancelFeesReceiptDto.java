package org.ideoholic.curium.model.feescategory.dto;

import lombok.Data;

@Data
public class CancelFeesReceiptDto {
	private String selectedBranchId;
	private String toDate;
	private String fromDate;
	private String oneDay;
	private String modeOfPayment;
	private String dayOne;
	private String dayFrom;
	private String dateTo;
	private String receiptId;
	private String journalId;
	private String feesReceiptId;
}
