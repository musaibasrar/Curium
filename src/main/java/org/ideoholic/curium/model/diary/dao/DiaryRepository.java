package org.ideoholic.curium.model.diary.dao;

import org.springframework.data.domain.Pageable;
import org.ideoholic.curium.model.diary.dto.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiaryRepository extends JpaRepository<Diary, Integer>{
	
	    Page<Diary> findByBranchid( int branchid, Pageable pageable);
	    Page<Diary> findByBranchidAndClasssec( int branchid, String classsec, Pageable pageable);
	    long countByBranchid(int branchid);
}
