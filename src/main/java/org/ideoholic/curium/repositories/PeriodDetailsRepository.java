package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.periods.dto.Perioddetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodDetailsRepository extends JpaRepository<Perioddetails, Integer> {

	List<Perioddetails> findByPeriodMasterIdperiodmasterOrderByIdperioddetailsAsc(Integer id);

	List<Perioddetails> findByStaffOrderByIdperioddetailsAsc(String teacherName);

}
