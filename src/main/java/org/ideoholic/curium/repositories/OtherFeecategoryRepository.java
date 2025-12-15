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

	    /**
	     * Replicates HQL:
	     * "from OtherFeecategory where particularname like '"+className+"--%' and academicyear = '"+searchYear+"' and branchid='"+branchId+"'"
	     *
	     * Usage: pass className, searchYear and branchId exactly as before.
	     */
	    @Query("SELECT o FROM OtherFeecategory o " +
	           "WHERE o.particularname LIKE CONCAT(:className, '--%') " +
	           "AND o.academicyear = :searchYear " +
	           "AND o.branchid = :branchId")
	    List<OtherFeecategory> findByClassNamePrefixAndAcademicYearAndBranchId(
	        @Param("className") String className,
	        @Param("searchYear") String searchYear,
	        @Param("branchId") String branchId
	    );

}
