package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.mess.stockentry.dto.MessInvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface MessInvoiceDetailsRepository extends JpaRepository<MessInvoiceDetails, Integer> {

	@Query("SELECT invoicedetails FROM MessInvoiceDetails invoicedetails "
			+ "WHERE invoicedetails.status <> :status AND invoicedetails.branchid = :branchId "
			+ "ORDER BY invoicedetails.invoicedate DESC")
	Page<MessInvoiceDetails> findActiveInvoicesByBranchId(@Param("status") String status, @Param("branchId") Integer branchId, Pageable pageable);

	@Query("SELECT COUNT(m) FROM MessInvoiceDetails m WHERE m.status <> :status AND m.branchid = :branchId")
	int countByStatusNotAndBranchid(@Param("status") String status, @Param("branchId") Integer branchid);

	@Modifying
	@Query("UPDATE MessInvoiceDetails m SET m.status = :status WHERE m.id = :id")
	void updateStatusById(@Param("status") String status, @Param("id") Integer id);

	@Query("SELECT m FROM MessInvoiceDetails m WHERE m.status <> :status AND m.branchid = :branchId AND m.suppliersid = :suppliersId ORDER BY m.invoicedate DESC")
	List<MessInvoiceDetails> fetchActiveInvoiceDetails(@Param("status") String status,
			@Param("branchId") Integer branchId, @Param("suppliersId") Integer suppliersId, Pageable pageable);

	@Modifying
	@Query("UPDATE MessInvoiceDetails m SET m.voucherid = :voucherId WHERE m.id = :id")
	void updateVoucherIdByInvoiceId(@Param("voucherId") Integer voucherId, @Param("id") Integer id);
}