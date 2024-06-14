package org.ideoholic.curium.model.documents.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentListAaResponseDto {
		private List<Parents> list;
		private boolean success;
}
