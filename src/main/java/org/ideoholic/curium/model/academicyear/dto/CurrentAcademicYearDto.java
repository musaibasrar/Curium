package org.ideoholic.curium.model.academicyear.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAcademicYearDto {

	private Integer currentayid;
	private String currentacademicyear;
}
