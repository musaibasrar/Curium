package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.marksdetails.dto.ExamRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRankRepository extends JpaRepository<ExamRank, Integer> {
    @Query("FROM ExamRank e WHERE e.student.sid = :sid AND e.exams.exid = :examid AND e.academicyear = :academicYear AND e.branchid = :branchId")
    ExamRank findByStudentIdExamIdAcademicYearAndBranchId(@Param("sid") int sid, @Param("examid") int examid, @Param("academicYear") String academicYear, @Param("branchId") int branchId);

    @Query("FROM ExamRank e WHERE e.exams.exid = :examid AND e.academicyear = :academicYear AND e.branchid = :branchId AND e.student.sid IN :studentIds")
    List<ExamRank> findByExamIdAcademicYearBranchIdAndStudentIdIn(@Param("examid") Integer examid, @Param("academicYear") String academicYear, @Param("branchId") int branchId, @Param("studentIds") List<Integer> studentIds);
}