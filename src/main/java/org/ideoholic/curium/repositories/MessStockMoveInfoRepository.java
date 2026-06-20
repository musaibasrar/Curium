package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.mess.item.dto.MessStockMoveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessStockMoveInfoRepository extends JpaRepository<MessStockMoveInfo, Integer> {
	MessStockMoveInfo findTopByOrderByReceiptnumberDesc();
	
	@Query("SELECT m FROM MessStockMoveInfo m WHERE m.date BETWEEN :fromDate AND :toDate AND m.studentname = :studentName AND m.branchid = :branchId")
	List<MessStockMoveInfo> findByDateRangeAndStudentAndBranch(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("studentName") String studentName, @Param("branchId") int branchId);
	
	@Query("SELECT m FROM MessStockMoveInfo m WHERE m.date BETWEEN :fromDate AND :toDate AND m.branchid = :branchId")
	List<MessStockMoveInfo> findByDateRangeAndBranch(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("branchId") int branchId);
}
