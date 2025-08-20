package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentFeesStructureRepository extends JpaRepository<Studentfeesstructure, Integer> {

	List<Studentfeesstructure> findByStudentSidAndFeescategoryIdfeescategoryIn(Integer sid, List<Integer> feescat);
	
	List<Studentfeesstructure> findByStudentSidAndAcademicyear(Integer sid, String academicYear);
}
