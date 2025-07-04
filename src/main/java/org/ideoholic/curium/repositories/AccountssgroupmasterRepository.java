package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.account.dto.Accountssgroupmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountssgroupmasterRepository extends JpaRepository<Accountssgroupmaster, Integer>{
	
	@Query("FROM Accountssgroupmaster WHERE accountSubGroupMaster.accountsubgroupmasterid = :accountSubGroupMasterId and branchid = :branchId")
	List<Accountssgroupmaster> findBySubgroupmasteridAndBranchid(@Param("accountSubGroupMasterId")int subgroupmasterid, @Param("branchId") int branchid);

}
