package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.employee.dto.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher,  Integer> {
	
	List<Teacher> findByBranchidOrderByTidDesc(Integer branchId);
	
	List<Teacher> findByBranchid(Integer branchId);
	
	List<Teacher> findByBranchidAndTidNotIn(Integer branchId, List<Integer> teacherIds);
	
	List<Teacher> findByTeachernameAndBranchidAndTidNotIn(String teacherName, Integer branchId, List<Integer> teacherIds);
	
	List<Teacher> findByDepartmentAndBranchidAndTidNotIn(String departmentName, Integer branchId, List<Integer> teacherIds);

	List<Teacher> findByCurrentemployeeAndBranchid(String currentEmployee, Integer branchId);
	
	@Query("SELECT t.teacherexternalid FROM Teacher t")
	List<String> fetchTeacherexternalid();
}
