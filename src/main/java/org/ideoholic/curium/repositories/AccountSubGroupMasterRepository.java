package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.account.dto.Accountgroupmaster;
import org.ideoholic.curium.model.account.dto.Accountsubgroupmaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountSubGroupMasterRepository extends JpaRepository<Accountsubgroupmaster, Integer>{
	
	List<Accountsubgroupmaster> findByAccountGroupMasterAndBranchid(Accountgroupmaster accountGroupMaster, int branchid);

}
