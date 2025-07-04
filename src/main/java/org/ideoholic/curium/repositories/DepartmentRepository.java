package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.department.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	List<Department> findByBranchid(Integer branchId);

}