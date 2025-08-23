package org.ideoholic.curium.repositories;

import java.util.Date;
import java.util.List;

import org.ideoholic.curium.model.job.dto.JobQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobQueryRepository extends JpaRepository<JobQuery, Integer> {

	Page<JobQuery> findByBranchidOrderByIdDesc(int branchid, Pageable pageable);

	int countByBranchid(int branchId);

	int countByStatusNot(String status);

	@Modifying
	@Query("UPDATE JobQuery j SET j.status = :status, j.updateddate = :updatedDate, j.updateduserid = :userId WHERE j.id IN :ids")
	void updateJobStatus(@Param("ids") List<Integer> ids, @Param("status") String status, @Param("userId") int userId,
			@Param("updatedDate") Date updatedDate);

	int countByStatusAndCreateddate(String status, Date createdDate);

	@Modifying
	@Query("UPDATE JobQuery jq SET jq.query = :parentQuery, jq.response = :response, jq.updateddate = :updatedDate, jq.updateduserid = :userId WHERE jq.id = :queryId")
	int updateJobQuery(@Param("queryId") int queryId, @Param("parentQuery") String parentQuery,
			@Param("response") String response, @Param("userId") int userId, @Param("updatedDate") Date updatedDate);

	@Query("SELECT jq FROM JobQuery jq WHERE jq.branchid = :branchId AND jq.teacher.tid = :tid ORDER BY jq.id DESC")
	List<JobQuery> findByBranchIdAndTeacherTid(@Param("branchId") int branchId, @Param("tid") int tid,
			Pageable pageable);

	@Query("SELECT COUNT(j) FROM JobQuery j WHERE j.teacher.tid = :tid AND j.branchid = :branchId")
	int countByTeacherIdAndBranchId(@Param("tid") int tid, @Param("branchId") int branchId);

	List<JobQuery> findByCreateddateBetweenAndStatusNot(Date fromDate, Date toDate, String status);

	int countByStatus(String status);

	List<JobQuery> findByStatusInAndCreateddate(List<String> status, Date createdDate);

	@Query("SELECT jq FROM JobQuery jq WHERE jq.id = :queryId AND jq.teacher.tid = :tid")
	JobQuery findByQueryIdAndStaffId(@Param("queryId") int queryId, @Param("tid") int tid);
	
	@Modifying
    @Query("UPDATE JobQuery j SET j.status = :status, j.updateddate = :updatedDate, j.updateduserid = :userId WHERE j.id = :jobId")
    void updateJobStatus(@Param("status") String status,@Param("updatedDate") Date updatedDate, @Param("userId") int userId, @Param("jobId") int jobId);

}
