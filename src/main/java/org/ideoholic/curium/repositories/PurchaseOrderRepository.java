package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.mess.item.dto.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	List<PurchaseOrder> findByExternalId(String externalId);
	
	@Modifying
	@Query("UPDATE PurchaseOrder p SET p.receivedQuantity = :receivedQuantity WHERE p.itemId = :itemId AND p.externalId = :poNumber")
	void updateReceivedQuantity(@Param("receivedQuantity")String receivedQuantity, @Param("itemId")Integer itemId, @Param("poNumber")String poNumber);
}