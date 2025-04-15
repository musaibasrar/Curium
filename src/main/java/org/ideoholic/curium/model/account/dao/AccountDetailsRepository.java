package org.ideoholic.curium.model.account.dao;

import java.util.List;

import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends JpaRepository<Accountdetails, Integer>{

	List<Accountdetails> findByBranchidOrderByAccountcodeAsc(int branchid);
	
	List<Accountdetails> findByBranchid(int branchid);

}