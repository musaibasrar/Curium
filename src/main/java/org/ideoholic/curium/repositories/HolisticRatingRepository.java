package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.ratingdetails.dto.HolisticRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HolisticRatingRepository extends JpaRepository<HolisticRating, Integer> {

    List<HolisticRating> findBySidIn(List<Integer> ids);

    List<HolisticRating> findBySidAndAssessmentsubjectidAndAssessmentidAndAcademicyear(
            Integer sid, Integer assessmentsubjectid, Integer assessmentid, String academicyear);

    List<HolisticRating> findBySidInAndAcademicyearAndBranchid(List<Integer> ids, String academicyear, int branchid);

    List<HolisticRating> findByAssessmentidAndBranchid(Integer assessmentid, int branchid);

    void deleteByRatingidIn(List<Integer> ratingids);

    @Modifying
    @Query("UPDATE HolisticRating hr SET hr.ratinggrade = :ratinggrade, hr.ratingvalue = :ratingvalue WHERE hr.ratingid = :ratingid")
    int updateRatingByRatingid(@Param("ratinggrade") String ratinggrade, @Param("ratingvalue") float ratingvalue,
            @Param("ratingid") Integer ratingid);

    @Query("SELECT hr, asub.subjectname, asm.category FROM HolisticRating hr "
            + "LEFT JOIN AssessmentSubject asub ON hr.assessmentsubjectid = asub.assessmentsubjectid "
            + "LEFT JOIN AssessmentSubjectMaster asm ON asub.subjectid = asm.subjectid "
            + "WHERE hr.sid = :studentId AND hr.academicyear = :academicYear AND hr.branchid = :branchId "
            + "ORDER BY asm.category ASC, asub.subjectname ASC")
    List<Object[]> fetchStudentProgressData(@Param("studentId") Integer studentId,
            @Param("academicYear") String academicYear, @Param("branchId") int branchId);
}
