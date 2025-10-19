package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.attendance.dto.Holidaysmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysMasterRepository extends JpaRepository<Holidaysmaster, Integer> {

	List<Holidaysmaster> findByAcademicyearAndBranchid(String academicyear, int branchid);

	List<Holidaysmaster> findByAcademicyearAndShidInAndBranchid(String academicyear,	List<Integer> holidaysIntList, int branchId);

	List<Holidaysmaster> findByAcademicyearAndShidIn(String academicyear, List<Integer> holidaysIntList);

}
