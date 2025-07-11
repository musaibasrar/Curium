package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeescategoryRepository extends JpaRepository<Feescategory, Integer> {

	List<Feescategory> findByAcademicyearAndBranchid(String academicYear, Integer branchId);
}
