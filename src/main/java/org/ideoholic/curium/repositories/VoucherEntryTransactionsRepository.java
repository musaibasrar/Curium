package org.ideoholic.curium.repositories;

import java.util.Date;
import java.util.List;

import org.ideoholic.curium.model.account.dto.VoucherEntrytransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherEntryTransactionsRepository extends JpaRepository<VoucherEntrytransactions, Integer> {

	@Query("FROM VoucherEntrytransactions v WHERE v.financialyear = :financialYear AND v.cancelvoucher = 'yes' AND v.branchid = :branchId ORDER BY v.transactionsid ASC")
	List<VoucherEntrytransactions> findCancelledVoucherEntryTransactions(@Param("financialYear") Integer financialYear,
			@Param("branchId") Integer branchId);

	VoucherEntrytransactions findByTransactionsid(Integer transactionsid);

	@Modifying
	@Query("UPDATE VoucherEntrytransactions v SET v.cancelvoucher = 'yes' WHERE v.transactionsid = :id")
	int cancelVoucher(@Param("id") int id);

	@Query("SELECT CASE WHEN count(v) > 0 THEN true ELSE false END FROM VoucherEntrytransactions v WHERE v.draccountid = :accountId OR v.craccountid = :accountId")
	boolean existsByDraccountidOrCraccountid(@Param("accountId") Integer accountId);

	List<VoucherEntrytransactions> findByNarrationLike(String supplierReferenceNo);

	@Query("SELECT v FROM VoucherEntrytransactions v WHERE v.transactiondate BETWEEN :fromDate AND :toDate AND v.financialyear = :financialYear AND v.cancelvoucher != 'yes' AND v.vouchertype = :voucherType AND v.branchid = :branchId ORDER BY v.transactionsid ASC")
	List<VoucherEntrytransactions> findVoucherEntries(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
			@Param("financialYear") Integer financialYear, @Param("branchId") int branchId,
			@Param("voucherType") int voucherType);

	@Query("SELECT v FROM VoucherEntrytransactions v " + "WHERE v.transactiondate BETWEEN :fromDate AND :toDate "
			+ "AND (v.draccountid = :accNo OR v.craccountid = :accNo) " + "AND v.cancelvoucher != 'yes' "
			+ "AND v.branchid = :branchId " + "ORDER BY v.transactionsid ASC")
	List<VoucherEntrytransactions> findTransactionsBetweenDates(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, @Param("accNo") int accNo, @Param("branchId") int branchId);

	@Query("from VoucherEntrytransactions where transactiondate BETWEEN :fromDate and :toDate and cancelvoucher!='yes' and branchid = :branchId order by transactionsid ASC")
	List<VoucherEntrytransactions> findByAllVoucherEntryTransactionsBetweenDates(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, @Param("branchId") int branchId);
	
	@Query("SELECT v FROM VoucherEntrytransactions v WHERE v.transactiondate BETWEEN :fromDate AND :toDate AND (v.draccountid IN :accountIds OR v.craccountid IN :accountIds) AND v.cancelvoucher <> 'yes' AND v.branchid = :branchId ORDER BY v.transactionsid ASC")
	List<VoucherEntrytransactions> findByAllVoucherEntryTransactionsBetweenDatesByIds(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate , @Param("accountIds") List<Integer> accountIds, @Param("branchId") int branchId);
}