package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.mess.item.dto.MessStockMoveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessStockMoveInfoRepository extends JpaRepository<MessStockMoveInfo, Integer> {
	MessStockMoveInfo findTopByOrderByReceiptnumberDesc();
}
