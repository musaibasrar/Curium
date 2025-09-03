package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.library.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByBranchid(Integer branchId);
}