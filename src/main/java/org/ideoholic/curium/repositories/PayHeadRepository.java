package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Payhead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayHeadRepository extends JpaRepository<Payhead, Integer> {

	List<Payhead> findByAcademicyearAndBranchid(String academicYear, Integer branchId);

	List<Payhead> findByPayheadtypeAndAcademicyearAndBranchid(String payHeadType, String academicYear, Integer branchId);
}
