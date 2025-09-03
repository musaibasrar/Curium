package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.library.dto.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Integer> {

	List<BookIssue> findByBookHolder(String bookHolder);
}
