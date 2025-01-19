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
public class BooksHistoryResponseDto {
	private List<BookHistory> booksHistoryList;
	
    @Builder.Default
    private boolean success = false;
}
