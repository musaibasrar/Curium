package org.ideoholic.curium.model.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
	
	private String transactionDateFrom;
	private String transactionDateTo;
	private String status;
	private String staffId;
	private String staffName;

}
