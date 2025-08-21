package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feescollection.dto.Feescollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeescollectionRepository extends JpaRepository<Feescollection, Integer> {
	
	List<Feescollection> findByStudentSidAndStudentFeeStructureSfsidIn(Integer sid, List<Integer> ids);

}
