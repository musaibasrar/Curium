package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.diary.dto.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

	Page<Diary> findByBranchid(int branchid, Pageable pageable);

	Page<Diary> findByBranchidAndClasssec(int branchid, String classsec, Pageable pageable);

	long countByBranchid(int branchid);
}
