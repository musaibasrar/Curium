package org.ideoholic.curium.model.adminexpenses.dao;

import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminExpensesRepository extends JpaRepository<Adminexpenses, Integer> {

}
