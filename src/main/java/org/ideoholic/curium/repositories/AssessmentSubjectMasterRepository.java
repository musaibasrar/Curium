package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.assessmentsubjectdetails.dto.AssessmentSubjectMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentSubjectMasterRepository extends JpaRepository<AssessmentSubjectMaster, Integer> {

    List<AssessmentSubjectMaster> findByBranchid(int branchid);

    void deleteBySubjectidIn(List<Integer> subjectIds);

    @Query("SELECT DISTINCT asm.category, asm.subjectid, asm.subjectname FROM AssessmentSubjectMaster asm "
            + "WHERE asm.branchid = :branchId AND asm.category IS NOT NULL "
            + "ORDER BY asm.category ASC, asm.subjectname ASC")
    List<Object[]> getCategoriesWithSubjects(@Param("branchId") int branchId);
}
