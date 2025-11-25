package org.ideoholic.curium.repositories;

import java.util.List;
import org.ideoholic.curium.model.subjectdetails.dto.SubSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSubjectRepository extends JpaRepository<SubSubject, Integer> {

    @Query("FROM SubSubject WHERE subjectid = :subjectId AND subsubjectname = :subSubject AND branchid = :branchId")
    SubSubject fetchSubSubjects(@Param("subjectId") int subjectId, @Param("subSubject") String subSubject, @Param("branchId") Integer branchId);

    List<SubSubject> findByBranchid(Integer branchId);

    void deleteBySubjectidIn(List<Integer> ids);

}