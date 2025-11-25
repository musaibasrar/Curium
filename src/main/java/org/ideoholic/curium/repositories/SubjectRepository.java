package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.subjectdetails.dto.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    
    List<Subject> findByExamclassAndBranchid(String examClass, Integer branchId);

    List<Subject> findByBranchid(int branchId);

    @Query("FROM Subject WHERE examclass = :examClass AND examname = :examName AND branchid = :branchId")
    List<Subject> fetchByExamSubject(@Param("examClass") String examClass, @Param("examName") String examName, @Param("branchId") Integer branchId);

    @Query("FROM Subject WHERE examclass = :examClass AND subjectid = :subId AND examname = :examName AND branchid = :branchId")
    Subject fetchByExamNameSubject(@Param("examClass") String examClass, @Param("subId") int subId, @Param("examName") String examName, @Param("branchId") Integer branchId);

    Subject findBySubid(Integer subid);

    void deleteBySubidIn(List<Integer> ids);

}