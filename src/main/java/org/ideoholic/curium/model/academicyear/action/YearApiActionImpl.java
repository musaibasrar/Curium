package org.ideoholic.curium.model.academicyear.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearDto;
import org.ideoholic.curium.model.academicyear.dto.CurrentAcademicYearResponseDto;
import org.ideoholic.curium.model.academicyear.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/yearProcess")
public class YearApiActionImpl implements YearApiAction {

	@Autowired
	private YearService yearService;

	@PostMapping("/saveYear")
	public ResponseEntity<ResultResponse> saveYear(@RequestBody CurrentAcademicYearDto currentAcademicYearDto) {
		log.debug("Action is viewAllExpenses");
		ResultResponse result = yearService.saveYear(currentAcademicYearDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}

	}

	@GetMapping("/updateYear")
	public ResponseEntity<CurrentAcademicYearResponseDto> updateYear() {
		log.debug("Action is addExpenses");
		return ResponseEntity.ok(yearService.updateYear());
	}
}
