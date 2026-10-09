package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessmentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolisticAssessmentScheduleRepository extends JpaRepository<HolisticAssessmentSchedule, Integer> {

    List<HolisticAssessmentSchedule> findByBranchid(int branchid);

    List<HolisticAssessmentSchedule> findByAssessmentnameAndClassesAndAcademicyearAndBranchid(String assessmentname,
            String classes, String academicyear, int branchid);

    List<HolisticAssessmentSchedule> findByAssessmentnameAndClassesAndAcademicyearAndBranchidOrderByDateAsc(
            String assessmentname, String classes, String academicyear, int branchid);

    void deleteByIdassessmentscheduleIn(List<Integer> scheduleIds);
}
