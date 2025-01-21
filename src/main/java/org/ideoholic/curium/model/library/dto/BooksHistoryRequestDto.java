package org.ideoholic.curium.model.library.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksHistoryRequestDto {
	private String studentExternalId;
	private String studentName;
	private String dateOfIssueFrom;
	private String dateOfIssueTo;
}
