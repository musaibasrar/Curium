package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.attendance.dto.Weeklyoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyoffRepository extends JpaRepository<Weeklyoff, Integer> {

	List<Weeklyoff> findByAcademicyearAndBranchid(String academicyear, int branchid);

	List<Weeklyoff> findByAcademicyearAndBranchidAndWidIn(String academicYear, int branchid,
			List<Integer> weeklyOffIds);

	List<Weeklyoff> findByAcademicyearAndWidIn(String academicYear, List<Integer> weeklyOffIds);
}
