package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Paybasic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaybasicRepository extends JpaRepository<Paybasic, Integer> {
	List<Paybasic> findByBranchid(Integer branchId);
}
