package org.ideoholic.curium.model.documents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchStudentDto {
		
	private String namesearch;
	private String admno;
	private String classsearch;
	private String secsearch;
}
