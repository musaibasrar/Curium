package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.student.dto.Studentotherfeesstructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentOtherFeesStructureRepository extends JpaRepository<Studentotherfeesstructure, Integer> {

	List<Studentotherfeesstructure> findByStudentSidAndOtherfeescategoryIdfeescategoryIn(Integer sid,
			List<Integer> feescat);
	
	List<Studentotherfeesstructure> findByStudentSidAndAcademicyear(Integer sid, String academicYear);

	Studentotherfeesstructure findByStudent_SidAndOtherfeescategory_IdfeescategoryAndAcademicyear(Integer sid, Integer idFeesCategory, String academicYear);

	@Modifying
    @Query("delete from Studentfeesstructure s where s.student.sid = :sid and s.sfsid in :ids")
    void deleteBySidAndSfsidIn(@Param("sid") int sid, @Param("ids") List<Integer> ids);
}
