package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.marksdetails.dto.SubjectGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectGradeRepository extends JpaRepository<SubjectGrade, Integer> {
    List<SubjectGrade> findByClasssecAndBranchid(String classsec, int branchid);
}