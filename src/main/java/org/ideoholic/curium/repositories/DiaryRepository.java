package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.diary.dto.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

	Page<Diary> findByBranchid(int branchid, Pageable pageable);

	Page<Diary> findByBranchidAndClasssec(int branchid, String classsec, Pageable pageable);

	long countByBranchid(int branchid);
	
	@Query("SELECT new com.yourpackage.DiaryDto(" +
		       "d.id, d.classsec, d.academicyear, d.branchid, d.subject, " +
		       "d.message, d.startdate, d.enddate, d.createddate, " +
		       "d.attachment1, d.attachment2, d.attachment3, d.userid) " +
		       "FROM Diary d WHERE d.branchid = :branchId")
		List<Diary> findDiaryByBranchId(@Param("branchId") int branchId, Pageable pageable);
}
