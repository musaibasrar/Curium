package org.ideoholic.curium.model.family.action;

import java.util.List;

import org.ideoholic.curium.model.family.dto.StudentSimpleDto;
import org.ideoholic.curium.model.family.service.FamilyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FamilyApiActionImpl implements FamilyApiAction {

	private final FamilyService familyService;

	public ResponseEntity<List<StudentSimpleDto>> getSiblings(Integer sid) {
		List<StudentSimpleDto> siblings = familyService.getSiblings(sid);
		return ResponseEntity.ok(siblings);
	}

	public ResponseEntity<List<StudentSimpleDto>> getOffsprings(Integer pid) {
		List<StudentSimpleDto> offsprings = familyService.getOffsprings(pid);
		return ResponseEntity.ok(offsprings);
	}
}
