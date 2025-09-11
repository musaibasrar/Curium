package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.marksdetails.dto.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {
    List<Marks> findByStudentSidIn(List<Integer> ids);

    @Query("FROM Marks m WHERE m.student.sid = :id AND m.subject.subid = :subjectId AND m.exam.exid = :examId")
    List<Marks> findByStudentIdSubjectIdExamId(@Param("id") Integer id, @Param("subjectId") int subjectId, @Param("examId") int examId);

    void deleteByMarksidInAndStudentSidIn(List<Integer> marksids, List<Integer> studentids);

    @Query("FROM Marks m WHERE m.student.sid = :id AND m.academicyear = :academicYear AND m.exam.exid = :examId ORDER BY m.exam.exid, m.subject.subid ASC")
    List<Marks> findByStudentIdAndAcademicyearAndExamId(@Param("id") int id, @Param("academicYear") String academicYear, @Param("examId") int examId);

    @Query("FROM Marks m WHERE m.student.sid = :sid AND m.exam.exid = :exid AND m.academicyear = :academicYear ORDER BY m.subject.subid ASC")
    List<Marks> findByStudentIdAndExamIdAndAcademicYear(@Param("sid") Integer sid, @Param("exid") Integer exid, @Param("academicYear") String academicYear);

    @Query("From Marks m where m.subject.subid = :subjectId and examid = :exId and m.student.sid IN (:studentIds)")
    List<Marks> findByStudentSubjectIdAndExamId(@Param("studentIds") List<Integer> studentIds, @Param("subjectId") int subjectId, @Param("exId") int exid);

    @Query("FROM Marks m WHERE m.student.sid = :sid AND m.subject.subid = :subid AND m.academicyear = :academicYear ORDER BY m.subject.subid ASC")
    List<Marks> findByStudentIdSubjectIdAndAcademicYear(@Param("sid") int sid, @Param("subid") int subid, @Param("academicYear") String academicYear);

    @Query("FROM Marks m WHERE m.student.sid = :sid AND m.academicyear = :academicYear AND m.exam.exid IN :exids ORDER BY m.subject.subid ASC")
    List<Marks> findByStudentIdAcademicYearAndExamIdIn(@Param("sid") Integer sid, @Param("academicYear") String academicYear, @Param("exids") List<Integer> exids);
}