package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessStockAvailabilityRepository extends JpaRepository<MessStockAvailability, Integer> {
	List<MessStockAvailability> findByAvailablestockGreaterThanAndMessitems_IdIn(Float availablestock, List<Integer> ids);

	void deleteByMessitems_IdIn(List<Integer> ids);

	@Query("FROM MessStockAvailability ms ORDER by ms.messitems.name ASC")
	List<MessStockAvailability> findAllOrderByMessitems();

	List<MessStockAvailability> findByAvailablestockGreaterThanOrderByMessitems_NameAsc(Float availablestock);

	@Modifying
	@Query("UPDATE MessStockAvailability m SET m.minstock = :minStock WHERE m.messitems.id = :itemId")
	void updateMinstockByItemid(@Param("minStock") Integer minStock, @Param("itemId") Integer itemId);

	@Modifying
	@Query("UPDATE MessStockAvailability m SET m.availablestock = m.availablestock - :quantity WHERE m.messitems.id = :itemId")
	void subtractAvailablestockByItemid(@Param("quantity") Float quantity, @Param("itemId") Integer itemId);

	@Modifying
	@Query("UPDATE MessStockAvailability m SET m.availablestock = m.availablestock + :quantity WHERE m.messitems.id = :itemId")
	void incrementAvailableStockByItemId(@Param("quantity") Float quantity, @Param("itemId") Integer itemId);
}