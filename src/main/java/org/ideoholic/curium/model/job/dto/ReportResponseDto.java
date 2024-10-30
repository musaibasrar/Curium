package org.ideoholic.curium.model.job.dto;

import java.util.List;

import org.ideoholic.curium.model.task.dto.Task;

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
	private String studentselected;
	private List<JobQuery> jobQueryList;
	private String transactionFromDateSelected;
	private String transactionToDateSelected;
	private String querycompleted;
	private boolean success;
	private List<Task> taskList;

}