package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.position.dto.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
	
	List<Position> findByBranchid(Integer branchId);

}
