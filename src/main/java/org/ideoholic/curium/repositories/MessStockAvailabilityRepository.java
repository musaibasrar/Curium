package org.ideoholic.curium.repositories;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.mess.stockentry.dto.MessStockAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessStockAvailabilityRepository extends JpaRepository<MessStockAvailability, Integer> {
	List<MessStockAvailability> findByAvailablestockGreaterThanAndMessItems_IdIn(Float availablestock, List<Integer> ids);

	void deleteByMessItems_IdIn(List<Integer> ids);

	@Query("FROM MessStockAvailability ms ORDER by ms.messItems.name ASC")
	List<MessStockAvailability> findAllOrderByMessItems();

	List<MessStockAvailability> findByAvailablestockGreaterThanOrderByMessItems_NameAsc(Float availablestock);

	@Modifying
	@Query("UPDATE MessStockAvailability m SET m.minstock = :minStock WHERE m.messItems.id = :itemId")
	void updateMinstockByItemid(@Param("minStock") Integer minStock, @Param("itemId") Integer itemId);

	@Modifying
	@Query("UPDATE MessStockAvailability m SET m.availablestock = m.availablestock - :quantity WHERE m.messItems.id = :itemId")
	void subtractAvailablestockByItemid(@Param("quantity") Float quantity, @Param("itemId") Integer itemId);

	@Modifying
	@Query("UPDATE MessStockAvailability m SET m.availablestock = m.availablestock + :quantity WHERE m.messItems.id = :itemId")
	void incrementAvailableStockByItemId(@Param("quantity") Float quantity, @Param("itemId") Integer itemId);

	Optional<MessStockAvailability> findByMessItems_id(Integer itemId);

	@Modifying
	@Query("UPDATE MessStockAvailability m SET m.availablestock = m.availablestock - :quantity WHERE m.messItems.id = :itemId")
	void decrementAvailableStock(@Param("quantity") Float quantity, @Param("itemId") Integer itemid);
}