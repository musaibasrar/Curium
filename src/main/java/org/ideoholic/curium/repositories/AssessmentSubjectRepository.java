package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentSubjectRepository extends JpaRepository<AssessmentSubject, Integer> {

    List<AssessmentSubject> findByAssessmentclassAndBranchid(String assessmentclass, int branchid);

    AssessmentSubject findByAssessmentsubjectid(Integer assessmentsubjectid);

    List<AssessmentSubject> findByBranchid(int branchid);

    void deleteByAssessmentsubjectidIn(List<Integer> subjectIds);
}
