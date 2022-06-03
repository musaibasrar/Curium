package org.ideoholic.curium.model.academicyear.dto;

import org.ideoholic.curium.model.student.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicyearDto {
	
	private Integer cayid;
	private String currentacademicyear;

}
