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
		
	private String nameSearch;
	private String admNo;
	private String classSearch;
	private String secSearch;
}
