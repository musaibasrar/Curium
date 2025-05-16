package org.ideoholic.curium.model.printids.dao;

import org.ideoholic.curium.model.mess.card.dto.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

}
