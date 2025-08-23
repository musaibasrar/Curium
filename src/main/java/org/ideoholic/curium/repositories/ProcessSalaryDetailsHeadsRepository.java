package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Processsalarydetailsheads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSalaryDetailsHeadsRepository extends JpaRepository<Processsalarydetailsheads, Integer> {

	List<Processsalarydetailsheads> findByProcesssalarydetailsIdprocesssalarydetails(Integer processId);

	// session.createQuery("from Processsalarydetailsheads where idprocesssalary="+processId+" and payheadname='Basic Pay'");
	Processsalarydetailsheads findByProcesssalarydetailsIdprocesssalarydetailsAndPayheadname(int processId, String string);

}
