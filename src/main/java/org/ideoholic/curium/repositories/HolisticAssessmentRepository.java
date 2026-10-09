package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolisticAssessmentRepository extends JpaRepository<HolisticAssessment, Integer> {

    List<HolisticAssessment> findByBranchid(int branchid);

    void deleteByAssessmentidIn(List<Integer> assessmentids);
}
