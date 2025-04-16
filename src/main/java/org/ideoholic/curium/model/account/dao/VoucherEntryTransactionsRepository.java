package org.ideoholic.curium.model.account.dao;

import java.util.List;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoucherEntryTransactionsRepository extends JpaRepository<VoucherEntrytransactions, Integer> {
	
	 @Query("FROM VoucherEntrytransactions v WHERE v.financialyear = :financialYear AND v.cancelvoucher = 'yes' AND v.branchid = :branchId ORDER BY v.transactionsid ASC")
	 List<VoucherEntrytransactions> findCancelledVoucherEntryTransactions(@Param("financialYear")Integer financialYear, @Param("branchId")Integer branchId);

	 VoucherEntrytransactions findByTransactionsid(Integer transactionsid);
	 
	    @Query("UPDATE VoucherEntrytransactions v SET v.cancelvoucher = 'yes' WHERE v.transactionsid = :id")
	    int cancelVoucher(@Param("id") int id);
	 
}
