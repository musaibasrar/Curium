package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.feesdetails.dto.Feesdetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeesdetailsRepository extends JpaRepository<Feesdetails, Integer> {
	
	List<Feesdetails> findBySidAndAcademicyear(int sid, String academicyear);

}
