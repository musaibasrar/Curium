package org.ideoholic.curium.model.academicyear.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface YearApiAction {

	ResponseEntity<ResultResponse> saveYear(@RequestBody CurrentAcademicYearDto currentAcademicYearDto);
	
	ResponseEntity<CurrentAcademicYearResponseDto> updateYear();
}
