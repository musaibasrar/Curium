package org.ideoholic.curium.repositories;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.mess.item.dto.MessItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessItemsRepository extends JpaRepository<MessItems, Integer> {
	List<MessItems> findAllByOrderByIdDesc();
	
	List<MessItems> findAllByOrderByIdAsc();

	List<MessItems> findByIdInOrderByIdDesc(List<Integer> ids);

	@Modifying
	@Query("UPDATE MessItems m SET m.name = :name, m.unitofmeasure = :unitOfMeasure WHERE m.id = :id")
	void updateNameAndUnitofmeasureById(@Param("name")String name, @Param("unitOfMeasure")String unitOfMeasure, @Param("id")Integer id);
	
	Optional<MessItems> findById(Integer id);
}