package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.stampfees.dto.Academicotherfeesstructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicOtherFeesStructureRepository extends JpaRepository<Academicotherfeesstructure, Integer> {
	Academicotherfeesstructure findBySidAndAcademicyear(Integer sid, String academicYear);

	void deleteBySidInAndAcademicyear(java.util.List<Integer> sids, String academicYear);
}
