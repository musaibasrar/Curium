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
public class BooksRequestDto {
	private String studentExternalId;
	private String admNo;
	private String admissionNumber;
	private String studentName;
	private String classAndSec;
	private String studentId;
	private Date issueDate;
	private String expectedReturnDate;
	private String[] bookIds;
	private String[] bookIssueIds;
	private String[] bookName;
	private String[] noOfDays;
}
