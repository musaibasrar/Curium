package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.ratingdetails.dto.AssessmentRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRankRepository extends JpaRepository<AssessmentRank, Integer> {

    List<AssessmentRank> findBySidAndAcademicyearAndBranchid(Integer sid, String academicyear, int branchid);

    List<AssessmentRank> findByAcademicyearAndAssessmentidAndBranchid(String academicyear, Integer assessmentid,
            int branchid);

    void deleteByIdIn(List<Integer> ids);
}
