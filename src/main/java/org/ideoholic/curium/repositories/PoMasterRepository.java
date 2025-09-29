package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.mess.item.dto.PoMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PoMasterRepository extends JpaRepository<PoMaster, Integer> {
	PoMaster findTopByOrderByIdDesc();

	void deleteByIdIn(List<Integer> ids);
	
	@Modifying
	@Query("UPDATE PoMaster p SET p.totalQuantityReceived = :receivedQuantity WHERE p.externalId = :poNumber")
	void updateTotalQuantityReceived(@Param("receivedQuantity")Integer receivedQuantity, @Param("poNumber")String poNumber);
}