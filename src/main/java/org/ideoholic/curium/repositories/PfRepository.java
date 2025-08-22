package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Pf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PfRepository extends JpaRepository<Pf, Integer> {

	List<Pf> findByBranchidOrderByDateDesc(int branchId);
}
