package org.ideoholic.curium.model.family.service;

import java.util.List;
import java.util.stream.Collectors;

import org.ideoholic.curium.model.family.dao.FamilyDao;
import org.ideoholic.curium.model.family.dto.StudentSimpleDto;
import org.ideoholic.curium.model.student.dto.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * Service to fetch family-related derived data (siblings, offsprings) and
 * return DTOs.
 *
 * Repository returns Student entities, and the service maps them to a
 * lightweight StudentSimpleDto to avoid returning entire entities (which could
 * trigger lazy-loading of unrelated associations).
 */
@Service
@RequiredArgsConstructor
public class FamilyService {

	private final FamilyDao familyDao;

	/**
	 * Get siblings of a student as DTOs. Returns an empty list if none found.
	 */
	@Transactional(readOnly = true)
	public List<StudentSimpleDto> getSiblings(Integer studentSid) {
		List<Student> siblings = familyDao.findSiblings(studentSid);
		return siblings.stream().map(this::toDto).collect(Collectors.toList());
	}

	/**
	 * Get offsprings (students) of a parent as DTOs. Returns an empty list if none
	 * found.
	 */
	@Transactional(readOnly = true)
	public List<StudentSimpleDto> getOffsprings(Integer parentPid) {
		List<Student> offsprings = familyDao.findOffSprings(parentPid);
		return offsprings.stream().map(this::toDto).collect(Collectors.toList());
	}

	/**
	 * Simple mapper from Student entity to StudentDto. Add or remove fields as
	 * required for your API/usage.
	 */
	private StudentSimpleDto toDto(Student s) {
		if (s == null) {
			return null;
		}
		StudentSimpleDto dto = new StudentSimpleDto();
		dto.setSid(s.getSid());
		dto.setName(s.getName());
		dto.setClassStudying(s.getClassstudying());
		dto.setAdmissionNumber(s.getAdmissionnumber());
		return dto;
	}
}
