package org.ideoholic.curium.model.printids.dao;

import java.util.List;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,  Integer> {
	
	List<Teacher> findByBranchidOrderByTidDesc(Integer branchId);
	
	List<Teacher> findByBranchid(Integer branchId);
}
