package org.ideoholic.curium.model.job.dao;

import java.util.Date;
import java.util.List;

import org.ideoholic.curium.model.job.dto.JobQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobQueryRepository extends JpaRepository<JobQuery, Integer> {
	
	Page<JobQuery> findByBranchidOrderByIdDesc(int branchid, Pageable pageable);
	
	int countByBranchid(int branchId);
	
	int countByStatusNot(String status);
	
	    @Modifying
	    @Query("UPDATE JobQuery j SET j.status = :status, j.updateddate = :updatedDate, j.updateduserid = :userId WHERE j.id IN :ids")
	    void updateJobStatus(@Param("ids") List<Integer> ids, @Param("status") String status, @Param("userId") int userId, @Param("updatedDate") Date updatedDate);

	    @Query("SELECT COUNT(jq) FROM JobQuery jq WHERE jq.status = 'Completed' AND jq.createddate = :updatedDate")
	    int countByStatusCompletedAndCreatedDate(@Param("updatedDate") Date updatedDate);
}
