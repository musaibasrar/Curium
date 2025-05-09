package org.ideoholic.curium.model.teachersperformance.dao;

import java.util.List;

import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherPerformanceRepository extends JpaRepository<Exams, Integer>{
	
	List<Exams> findByBranchid(int branchid);

}
