package org.ideoholic.curium.model.account.dao;

import java.util.List;

import org.ideoholic.curium.model.account.dto.Accountdetailsbalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsBalanceRepository extends JpaRepository<Accountdetailsbalance, Integer>{
	
	 List<Accountdetailsbalance> findByBranchid(int branchid);

}
