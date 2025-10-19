package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.examdetails.dto.Examschedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamScheduleRepository extends JpaRepository<Examschedule, Integer> {

	List<Examschedule> findByBranchid(int branchid);
	
    List<Examschedule> findByClassesAndAcademicyearAndExamnameAndBranchidOrderByDateAsc(
            String classes, 
            String academicYear, 
            String exam, 
            Integer branchId);

}
