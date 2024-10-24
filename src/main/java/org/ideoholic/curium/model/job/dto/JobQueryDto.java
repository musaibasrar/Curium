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
public class JobQueryDto {
	private List<JobQuery> queriesList;
	private List<Task> taskList;
	private int noOfPages;
	private int page;
	private int currentPage;
	private String querycompleted;
	private boolean success;
}