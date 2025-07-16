package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamsRepository extends JpaRepository<Exams, Integer> {

	List<Exams> findByBranchid(Integer branchid);
	
	List<Exams> findByExidInAndBranchid(List<Integer> ids, Integer branchId);

}
