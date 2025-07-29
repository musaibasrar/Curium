package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentOtherFeesStructureRepository extends JpaRepository<Studentotherfeesstructure, Integer> {

	List<Studentotherfeesstructure> findByStudentSidAndOtherfeescategoryIdfeescategoryIn(Integer sid,
			List<Integer> feescat);
	
	List<Studentotherfeesstructure> findByStudentSidAndAcademicyear(Integer sid, String academicYear);
}
