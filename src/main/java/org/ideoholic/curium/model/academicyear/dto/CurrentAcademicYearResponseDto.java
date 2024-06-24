package org.ideoholic.curium.model.academicyear.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAcademicYearResponseDto {

	private Integer currentayid;
	private String currentacademicyear;
	private boolean success;
}
