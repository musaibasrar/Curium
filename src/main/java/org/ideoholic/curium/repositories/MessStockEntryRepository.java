package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.mess.stockentry.dto.MessStockEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessStockEntryRepository extends JpaRepository<MessStockEntry, Integer> {
	List<MessStockEntry> findByAvailablequantityGreaterThanAndStatusNotOrderByItemidDesc(Float availablequantity, String status);

	List<MessStockEntry> findByMessinvoicedetails_Id(Integer messinvoicedetailsId);

	List<MessStockEntry> findByIdIn(List<Integer> ids);

	@Modifying
	@Query("UPDATE MessStockEntry m SET m.status = :status WHERE m.messinvoicedetails.id = :invoiceDetailsId")
	void updateStatusByInvoicedetailsid(@Param("status")String status, @Param("invoiceDetailsId")Integer invoiceDetailsId);
	
	// "From MessStockEntry mse where mse.status != 'CANCELLED' and mse.messinvoicedetails.id = :invoiceDetailsId and mse.messinvoicedetails.status != 'CANCELLED' order by mse.id DESC"
	@Query("FROM MessStockEntry mse WHERE mse.status != :status AND mse.messinvoicedetails.id = :invoiceDetailsId AND mse.messinvoicedetails.status != :status ORDER BY mse.id DESC")
	List<MessStockEntry> findMRVDetailsByInvoiceDetailsId(@Param("invoiceDetailsId")Integer invoiceDetailsId, @Param("status")String status);

	// "From MessStockEntry mse where mse.status != 'CANCELLED' and mse.itemid = :itemId and mse.messinvoicedetails.status !='CANCELLED' order by mse.id ASC"
	@Query("FROM MessStockEntry mse WHERE mse.status != :status AND mse.itemid = :itemId AND mse.messinvoicedetails.status != :status ORDER BY mse.id ASC")
	List<MessStockEntry> findItemsStockEntryByItemId(@Param("itemId")Integer itemId, @Param("status")String status);
}