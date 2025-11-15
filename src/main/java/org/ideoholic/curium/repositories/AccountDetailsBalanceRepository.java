package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.ideoholic.curium.model.account.dto.Accountdetailsbalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsBalanceRepository extends JpaRepository<Accountdetailsbalance, Integer> {

	@Query("SELECT accdetails FROM Accountdetailsbalance accdetails " + "JOIN accdetails.accountDetails ad "
			+ "JOIN ad.accountGroupMaster agm "
			+ "WHERE agm.accountgroupid IN (:accountIds) AND accdetails.branchid = :branchId")
	List<Accountdetailsbalance> findAllByBranchIdAndAccountIdsIn(@Param("branchId") int branchId,
			@Param("accountIds") List<Integer> accountIds);

	List<Accountdetailsbalance> findByBranchid(int branchid);

	List<Accountdetailsbalance> findByAccountDetailsInAndBranchid(List<Accountdetails> accountdetailsid, int branchid);

	Accountdetailsbalance findByAccountDetails(Accountdetails accountDetails);

	@Query("SELECT a FROM Accountdetailsbalance a " + "WHERE a.accountDetails.accountGroupMaster.accountgroupid IN (1) "
			+ "AND a.branchid = :branchId")
	List<Accountdetailsbalance> findBankCashAccountDetailsByBranch(@Param("branchId") int branchId);

    @Modifying
    @Query("delete from Accountdetailsbalance a where a.accountDetails.accountdetailsid IN :ids")
    int deleteByAccountdetailsidIn(@Param("ids") List<Integer> ids);

    Accountdetailsbalance findByAccountDetails_Accountdetailsid(Integer accountdetailsid);
}