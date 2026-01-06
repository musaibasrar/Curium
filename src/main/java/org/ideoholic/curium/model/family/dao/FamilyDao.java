package org.ideoholic.curium.model.family.dao;

import java.util.Collections;
import java.util.List;

import org.ideoholic.curium.model.family.dto.Family;
import org.ideoholic.curium.model.family.dto.FamilyMember;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.repositories.FamilyMemberRepository;
import org.ideoholic.curium.repositories.FamilyRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FamilyDao {

	private final FamilyMemberRepository familyMemberRepo;
	private final FamilyRepository familyRepo;

	public List<Student> findSiblings(Integer studentSid) {
		return familyMemberRepo.findSiblingsByStudentSid(studentSid);
	}
	
	public FamilyMember addStudentToFamily(Family family, Student student) {
		FamilyMember familyMember = familyMemberRepo.save(new FamilyMember(family, student));
		return familyMember;
	}
	
	public FamilyMember addParentToFamily(Family family, Parents parent) {
		FamilyMember familyMember = familyMemberRepo.save(new FamilyMember(family, parent));
		return familyMember;
	}

	public List<Student> findOffSprings(Integer parentPid) {
		List<Student> offsprings = familyMemberRepo.findOffspringsByParentPid(parentPid);
		if (CollectionUtils.isEmpty(offsprings)) {
			return Collections.emptyList();
		}
		return offsprings;
	}
	
	public Family findParentsFamily(Integer parentPid) {
		return familyRepo.findFirstByMembersParentPid(parentPid).orElse(null);
	}
	
	public Family findStudentFamily(Integer studentSid) {
		return familyRepo.findFirstByMembersStudentSid(studentSid).orElse(null);
	}
}
