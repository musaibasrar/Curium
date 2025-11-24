package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.subjectdetails.dto.Subjectmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectMasterRepository extends JpaRepository<Subjectmaster, Integer> {

    List<Subjectmaster> findByBranchid(int branchId);

    void deleteBySubjectidIn(List<Integer> ids);

}