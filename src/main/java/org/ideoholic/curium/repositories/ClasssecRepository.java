package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.std.dto.Classsec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasssecRepository extends JpaRepository<Classsec, Integer> {

	@Query("FROM Classsec WHERE branchid = :branchId")
	List<Classsec> findByBranchid(@Param("branchId") int branchId);

}