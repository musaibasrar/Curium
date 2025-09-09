package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feescategory.dto.Feescategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeesCategoryRepository extends JpaRepository<Feescategory, Integer> {

	List<Feescategory> findByAcademicyearAndBranchid(String academicYear, Integer branchId);
	
	@Query("SELECT f FROM Feescategory f " +
	           "WHERE f.particularname LIKE CONCAT(:classname, '--%') " +
	           "AND f.academicyear = :searchYear " +
	           "AND f.branchid = :branchId")
	    List<Feescategory> findFeecategoryOfStudent(String classname, String searchYear, String branchId);
	
	List<Feescategory> findByBranchidAndAcademicyearIn(int branchId, List<String> academicYears);

}
