package org.ideoholic.curium.model.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksRequestDto {
	private String studentExternalId;
	private String admNo;
	private String admissionNumber;
	private String classAndSec;
	private String studentId;
	private String dateOfFeesDetails;
	private String[] bookIds;
	private String transactionDate;
}
