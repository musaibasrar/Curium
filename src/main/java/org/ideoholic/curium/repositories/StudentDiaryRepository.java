package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.studentdiary.dto.StudentDiary;
import org.ideoholic.curium.model.studentdiary.dto.StudentDiaryProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDiaryRepository extends JpaRepository<StudentDiary, Integer> {
	int countByBranchidAndSid(int branchid, int sid);
	
	int countByBranchid(int branchid);
	
	 @Query("SELECT d.id AS id, d.sid AS sid, s.name AS name, d.classsec AS classsec, " +
	           "d.academicyear AS academicyear, d.branchid AS branchid, d.subject AS subject, " +
	           "d.message AS message, d.createddate AS createddate, d.userid AS userid " +
	           "FROM StudentDiary d JOIN Student s ON d.sid = s.sid " +
	           "WHERE d.branchid = :branchId AND d.sid = :sid")
	    List<StudentDiaryProjection> findByBranchIdAndSid(@Param("branchId") int branchId,
	                                                      @Param("sid") int sid,
	                                                      Pageable pageable);
	 
	 @Query("SELECT d.id AS id, d.sid AS sid, s.name AS name, d.classsec AS classsec, " +
	           "d.academicyear AS academicyear, d.branchid AS branchid, d.subject AS subject, " +
	           "d.message AS message, d.createddate AS createddate, d.userid AS userid " +
	           "FROM StudentDiary d JOIN Student s ON d.sid = s.sid " +
	           "WHERE d.branchid = :branchId ORDER BY d.createddate DESC")
	    List<StudentDiaryProjection> findDiaryByBranchId(@Param("branchId") int branchId, Pageable pageable);

}
