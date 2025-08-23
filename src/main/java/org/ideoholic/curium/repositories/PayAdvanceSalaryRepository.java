package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Payadvancesalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayAdvanceSalaryRepository extends JpaRepository<Payadvancesalary, Integer> {

	List<Payadvancesalary> findByStatusAndBranchid(String status, int branchid);

	List<Payadvancesalary> findByBranchidAndStatusIn(int branchid, List<String> statuses);
}
