package org.ideoholic.curium.repositories;

import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.mess.stockmove.dto.MessStockMove;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessStockMoveRepository extends JpaRepository<MessStockMove, Integer> {
	Optional<MessStockMove> findTopByOrderByIdDesc();

	List<MessStockMove> findByStatusNotAndIssuedtoAndItemidAndBranchidOrderByIdDesc(String status, String issuedTo, Integer itemId, Integer branchId);

	@Modifying
	@Query("UPDATE MessStockMove SET voucherid = :transactionsId WHERE id = :id")
	void updateVoucherId(@Param("transactionsId") Integer transactionsId, @Param("id") Integer id);

	@Query("FROM MessStockMove msm where msm.status != :status order by msm.id DESC")
	Page<MessStockMove> findByStatusAndId(@Param("status") String status, Pageable page);

	int countByStatusAndBranchid(String status, Integer branchId);

}
