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
public class JobQueryDto {
	private List<JobQuery> queriesList;
	private int noOfPages;
	private int page;
	private int currentPage;
	private boolean success;
}