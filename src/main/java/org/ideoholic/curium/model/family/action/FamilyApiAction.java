package org.ideoholic.curium.model.family.action;


import java.util.List;

import org.ideoholic.curium.model.family.dto.StudentSimpleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FamilyController
 * Exposes simple endpoints to fetch siblings (for a student) and offsprings (for a parent).
 *
 * - GET /api/family/siblings/{sid}
 * - GET /api/family/offsprings/{pid}
 *
 * Responses: 200 OK with JSON array (may be empty).
 */
@RequestMapping("/api/v1/familyProcess")
public interface FamilyApiAction {

	    /**
	     * Get siblings of a student as a list of StudentDto.
	     * Returns 200 OK with an empty list if none found.
	     */
		@GetMapping("/siblings/{sid}")
		public ResponseEntity<List<StudentSimpleDto>> getSiblings(@PathVariable("sid") Integer sid);

	    /**
	     * Get offsprings (students) of a parent as a list of StudentDto.
	     * Returns 200 OK with an empty list if none found.
	     */
		@GetMapping("/offsprings/{pid}")
		public ResponseEntity<List<StudentSimpleDto>> getOffsprings(@PathVariable("pid") Integer pid);
	}
