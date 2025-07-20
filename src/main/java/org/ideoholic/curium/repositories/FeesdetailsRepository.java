package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeesdetailsRepository extends JpaRepository<Feesdetails, Integer> {
	
	List<Feesdetails> findByStudent_sidAndAcademicyear(int sid, String academicyear);
	
    @Query("SELECT SUM(f.grandtotal) FROM Feesdetails f WHERE f.student.sid = :sid AND f.academicyear = :currentYear")
    String sumGrandTotalBySidAndAcademicyear(@Param("sid") int sid, @Param("currentYear") String currentYear);


}
