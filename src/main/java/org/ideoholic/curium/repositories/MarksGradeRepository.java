package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.marksdetails.dto.MarksGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarksGradeRepository extends JpaRepository<MarksGrade, Integer> {
    List<MarksGrade> findByBranchid(int branchid);
}