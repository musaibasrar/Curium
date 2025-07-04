package org.ideoholic.curium.model.task.dao;

import java.util.List;

import org.ideoholic.curium.model.task.dto.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	 List<Task> findByJobquery_Id(int jobId);
	 
	 Page<Task> findByBranchidOrderByIdDesc(int branchid, Pageable pageable);
	 
	 int countByBranchid(int branchId);

}
