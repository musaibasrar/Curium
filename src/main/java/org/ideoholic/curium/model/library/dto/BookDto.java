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
public class BookDto {
	private String bId;
	private String bookname;
	private String subject;
	private String author;
	private String publisher;
	private String isbn;
	private String status;
	private String bookHolder;
	private String shelf;
	private Date startdate;
	private Date enddate;
	private String noofdays;
	private String studentExternalId;
	private String transactionDate;
}
