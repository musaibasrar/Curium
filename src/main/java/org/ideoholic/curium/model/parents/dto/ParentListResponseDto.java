package org.ideoholic.curium.model.parents.dto;

import java.util.List;

import org.ideoholic.curium.model.std.dto.Classsec;

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
		private List<Parents> parentsList;
		private List<Classsec> classsecList;
		private int page;
		private int noOfPages;
		private boolean success;
}
