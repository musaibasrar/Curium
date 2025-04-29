package org.ideoholic.curium.model.account.dao;

import java.util.Date;
import java.util.List;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoucherEntryTransactionsRepository extends JpaRepository<VoucherEntrytransactions, Integer> {
	
	 @Query("FROM VoucherEntrytransactions v WHERE v.financialyear = :financialYear AND v.cancelvoucher = 'yes' AND v.branchid = :branchId ORDER BY v.transactionsid ASC")
	 List<VoucherEntrytransactions> findCancelledVoucherEntryTransactions(@Param("financialYear")Integer financialYear, @Param("branchId")Integer branchId);

	 VoucherEntrytransactions findByTransactionsid(Integer transactionsid);
	 
	    @Modifying
	    @Query("UPDATE VoucherEntrytransactions v SET v.cancelvoucher = 'yes' WHERE v.transactionsid = :id")
	    int cancelVoucher(@Param("id") int id);
	    
	    @Query("SELECT CASE WHEN count(v) > 0 THEN true ELSE false END FROM VoucherEntrytransactions v WHERE v.draccountid = :accountId OR v.craccountid = :accountId")
	    boolean existsByDraccountidOrCraccountid(@Param("accountId") Integer accountId);
	    
	    List<VoucherEntrytransactions> findByNarrationLike(String supplierReferenceNo);
	    
	    @Query("SELECT v FROM VoucherEntrytransactions v WHERE v.transactiondate BETWEEN :fromDate AND :toDate AND v.financialyear = :financialYear AND v.cancelvoucher != 'yes' AND v.vouchertype = :voucherType AND v.branchid = :branchId ORDER BY v.transactionsid ASC")
	    List<VoucherEntrytransactions> findVoucherEntries(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("financialYear") Integer financialYear, @Param("branchId") int branchId, @Param("voucherType") int voucherType);
	 
}
