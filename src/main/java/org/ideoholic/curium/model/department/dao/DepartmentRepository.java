package org.ideoholic.curium.model.department.dao;

import org.ideoholic.curium.model.department.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    @Query("From Department where branchid=:branchId")
    List<Department> findByBranchId(int branchId);
}
