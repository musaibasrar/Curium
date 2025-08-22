package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Leavetypemaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeMasterRepository extends JpaRepository<Leavetypemaster, Integer> {
    List<Leavetypemaster> findByBranchid(int branchId);
}


