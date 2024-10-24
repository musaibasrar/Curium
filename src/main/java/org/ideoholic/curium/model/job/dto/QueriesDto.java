package org.ideoholic.curium.model.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueriesDto {
	
	private String[] queryIds;
	private String[] taskIds;
	private String jobId;

}
