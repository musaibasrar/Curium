package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feescollection.dto.Receiptinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptinfoRepository extends JpaRepository<Receiptinfo, Integer> {

	Receiptinfo findTopByBranchidOrderByReceiptnumberDesc(Integer branchid);

	List<Receiptinfo> findByStudent_SidAndAcademicyearAndCancelreceipt(Integer sid, String academicyear, Integer cancelreceipt);
	
	@Query("SELECT r FROM Receiptinfo r " +
		       "WHERE r.student.sid = :sid " +
		       "AND r.academicyear = :academicyear " +
		       "AND (r.cancelreceipt = :cancelreceipt OR r.cancelreceipt IS NULL)")
	List<Receiptinfo> findByStudentSidAndAcademicyearAndCancelreceiptOrNull(
		        @Param("sid") Integer sid,
		        @Param("academicyear") String academicYear,
		        @Param("cancelreceipt") Integer cancelReceipt);
}
