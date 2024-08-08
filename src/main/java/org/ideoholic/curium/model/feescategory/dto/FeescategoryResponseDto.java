package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;

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
public class FeescategoryResponseDto {
		
	private List<Feescategory> feescategory;
	private List<Parents> searchStudentList;
	private boolean success;
}
