package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Studentfeesstructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentFeesStructureRepository extends JpaRepository<Studentfeesstructure, Integer> {

	List<Studentfeesstructure> findByStudentSidAndFeescategoryIdfeescategoryIn(Integer sid, List<Integer> feescat);
	
	List<Studentfeesstructure> findByStudentSidAndAcademicyear(Integer sid, String academicYear);
	
    @Modifying
    @Query("delete from Studentfeesstructure s where s.student.sid = :sid and s.sfsid in :ids")
    void deleteBySidAndSfsidIn(@Param("sid") int sid, @Param("ids") List<Integer> ids);
}
