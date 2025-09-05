package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.stampfees.dto.Academicfeesstructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicFeesStructureRepository extends JpaRepository<Academicfeesstructure, Integer> {
	
	@Query("SELECT a.totalfees FROM Academicfeesstructure a WHERE a.sid = :sid AND a.academicyear = :currentYear")
    String getTotalFees(@Param("sid") int sid, @Param("currentYear") String currentYear);

}
