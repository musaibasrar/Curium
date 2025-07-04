package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.account.dto.Financialaccountingyear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialAccountingYearRepository extends JpaRepository<Financialaccountingyear, Integer> {

	Financialaccountingyear findByActiveAndBranchid(String active, int branchid);

}
