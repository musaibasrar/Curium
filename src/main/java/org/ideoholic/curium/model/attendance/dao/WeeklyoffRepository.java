package org.ideoholic.curium.model.attendance.dao;

import java.util.List;

import org.ideoholic.curium.model.attendance.dto.Weeklyoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyoffRepository extends JpaRepository<Weeklyoff, Integer> {
	
	List<Weeklyoff> findByAcademicyearAndBranchid(String academicyear, int branchid);
}
