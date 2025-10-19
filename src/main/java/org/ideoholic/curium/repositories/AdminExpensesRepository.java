package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.adminexpenses.dto.Adminexpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminExpensesRepository extends JpaRepository<Adminexpenses, Integer> {

    List<Adminexpenses> findByBranchid(Integer branchId);

    @Query("From Adminexpenses where idAdminExpenses=:expensesIds  and branchid=:branchId")
    Adminexpenses findByExpenseId(@Param("expensesIds") Integer expensesIds,@Param("branchId") Integer  branchId);

}
