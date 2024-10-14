package org.ideoholic.curium.model.job.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {
	
	private String statusSelected;
	private String staffSelected;
	private List<JobQuery> JobQueryList;
	private String transactionFromDateSelected;
	private String transactionToDateSelected;

}