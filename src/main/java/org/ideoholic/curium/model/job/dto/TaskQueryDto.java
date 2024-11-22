package org.ideoholic.curium.model.job.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskQueryDto {
	private String queryId; 
	private String jobQuery;
	private String response;
	private String queryRemarks;
	private String[] queryIds;
	private String[] taskIds;
	private String jobId;
	private String jobno;
	private String[] assignto;
	private String[] task;
	private String[] description;
	private String[] expecteddd;
	private String displayType;



}
