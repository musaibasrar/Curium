package org.ideoholic.curium.model.parents.dto;

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
public class ParentListResponseDto {
		private List<Parents> list;
		private int page;
		private int noOfPages;
		private boolean success;
}
