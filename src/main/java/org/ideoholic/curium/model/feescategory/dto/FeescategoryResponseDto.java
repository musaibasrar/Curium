package org.ideoholic.curium.model.feescategory.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class FeescategoryResponseDto {
		
	private List<Feescategory> feescategory;
	private boolean success;
}
