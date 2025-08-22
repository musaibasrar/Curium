package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Leaveapplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<Leaveapplication, Integer> {

	List<Leaveapplication> findByAcademicyearAndBranchid(String currentAcademicYear, Integer branchId);
}


