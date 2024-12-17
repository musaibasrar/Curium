package org.ideoholic.curium.model.library.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class BooksResponseDto {
	private Book book;
	private List<Book> booksList;
	private List<Book> availableList;
	private List<Book> issuedList;
	private String studentNameDetails;
	private String admnoDetails;
	private String classAndSecDetails;
	private String studentIdDetails;
	private String dateOfFeesDetails;
	
    @Builder.Default
    private boolean success = false;
}
