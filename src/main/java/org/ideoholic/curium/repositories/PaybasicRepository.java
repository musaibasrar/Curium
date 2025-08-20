package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Paybasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaybasicRepository extends JpaRepository<Paybasic, Integer> {
	List<Paybasic> findByBranchid(Integer branchId);
}
