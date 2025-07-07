package org.ideoholic.curium.repositories;

import java.util.Date;
import java.util.List;

import org.ideoholic.curium.model.task.dto.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByJobquery_Id(int jobId);

	Page<Task> findByBranchidOrderByIdDesc(int branchid, Pageable pageable);

	int countByBranchid(int branchId);

	@Query("SELECT t FROM Task t WHERE t.branchid = :branchId AND t.teacher.tid = :tid ORDER BY t.id DESC")
	List<Task> findByBranchIdAndTeacherTid(@Param("branchId") int branchId, @Param("tid") int tid, Pageable pageable);
	
	 @Query("SELECT COUNT(t) FROM Task t WHERE t.teacher.tid = :tid AND t.branchid = :branchId")
	 int countByBranchIdAndTeacherTid(@Param("branchId") int branchId, @Param("tid") int tid);
	 
	    @Modifying
	    @Query("UPDATE Task t SET t.status = :status, t.updateddate = :updatedDate, t.updateduserid = :userId WHERE t.id = :taskId")
	    void updateTaskToCompleted(@Param("status") String status,@Param("updatedDate") Date updatedDate,@Param("userId") int userId, @Param("taskId") int taskId);

	    List<Task> findByIdIn(List<Integer> taskIds);

}
