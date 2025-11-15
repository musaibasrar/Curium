package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.periods.dto.Periodmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodMasterRepository extends JpaRepository<Periodmaster, Integer> {

	List<Periodmaster> findByAcademicyearAndBranchid(String ademicYear, Integer branchId);

}
