package org.ideoholic.curium.model.job.dao;

import java.time.LocalDate;
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
	    @Query("UPDATE JobQuery j SET j.status = 'Completed', j.updateddate = :updatedDate, j.updateduserid = :userId WHERE j.id IN :ids")
	    void markQueriesAsCompleted(@Param("ids") List<Integer> ids, @Param("userId") int userId, @Param("updatedDate") Date updatedDate);

	    @Modifying
	    @Query("UPDATE JobQuery jq SET jq.status = 'Cancelled', jq.updateduserid = :userId, jq.updateddate = CURRENT_DATE WHERE jq.id IN :queryIds")
	    int cancelQueries(@Param("queryIds") List<Integer> queryIds, @Param("userId") int userId);
}
