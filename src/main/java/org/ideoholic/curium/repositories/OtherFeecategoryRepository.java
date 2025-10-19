package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feescategory.dto.OtherFeecategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OtherFeecategoryRepository extends JpaRepository<OtherFeecategory, Integer> {
	
	@Query("SELECT o FROM OtherFeecategory o " +
	           "WHERE (o.academicyear = :academicYear OR o.academicyear = :nextYear) " +
	           "AND o.branchid = :branchId")
	    List<OtherFeecategory> findByBranchAndAcademicYear(
	            @Param("branchId") int branchId,
	            @Param("academicYear") String academicYear,
	            @Param("nextYear") String nextYear);

}
