package org.ideoholic.curium.model.account.dao;

import java.util.List;

import org.ideoholic.curium.model.account.dto.Accountdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends JpaRepository<Accountdetails, Integer> {

	List<Accountdetails> findByBranchidOrderByAccountcodeAsc(int branchid);

	List<Accountdetails> findByBranchid(int branchid);

	@Query("SELECT a FROM Accountdetails a WHERE (a.accountname = :accountName OR a.accountcode = :accountCode) AND a.branchid = :branchId")
	Accountdetails findAccountDetails(@Param("accountName") String accountName,
			@Param("accountCode") String accountCode, @Param("branchId") int branchId);
}