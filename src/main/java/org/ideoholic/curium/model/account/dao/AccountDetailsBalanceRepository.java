package org.ideoholic.curium.model.account.dao;

import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.model.account.dto.Accountdetailsbalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDetailsBalanceRepository extends JpaRepository<Accountdetailsbalance, Integer>{

	@Query("SELECT accdetails FROM Accountdetailsbalance accdetails " +
		"JOIN accdetails.accountDetails ad " +
		"JOIN ad.accountGroupMaster agm " +
		"WHERE agm.accountgroupid IN (:accountIds) AND accdetails.branchid = :branchId")
	List<Accountdetailsbalance> findAllByBranchIdAndAccountIdsIn(@Param("branchId")int branchId, @Param("accountIds")List<Integer> accountIds);

    List<Accountdetailsbalance> findByBranchid(int branchid);
}