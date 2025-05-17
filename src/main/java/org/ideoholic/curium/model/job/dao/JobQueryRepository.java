package org.ideoholic.curium.model.job.dao;

import org.ideoholic.curium.model.job.dto.JobQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobQueryRepository extends JpaRepository<JobQuery, Integer> {
	
	Page<JobQuery> findByBranchidOrderByIdDesc(int branchid, Pageable pageable);

}
