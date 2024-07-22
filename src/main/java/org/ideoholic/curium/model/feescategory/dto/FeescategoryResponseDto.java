package org.ideoholic.curium.model.feescategory.dto;

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
@JsonInclude
public class FeescategoryResponseDto {
		
	private List<Feescategory> feescategory;
	private boolean success;
}
