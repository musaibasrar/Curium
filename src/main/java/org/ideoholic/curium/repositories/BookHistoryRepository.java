package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.library.dto.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory, Integer> {

	List<BookHistory> findByIssueDateBetween(String fromDate, String toDate);
}
