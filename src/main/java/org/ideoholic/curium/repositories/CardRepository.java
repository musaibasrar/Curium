package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.mess.card.dto.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

	List<Card> findBySidIn(List<Integer> sids);
}
