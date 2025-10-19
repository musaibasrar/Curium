package org.ideoholic.curium.repositories;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Payheadstaffdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayHeadStaffDetailsRepository extends JpaRepository<Payheadstaffdetails, Integer> {

	List<Payheadstaffdetails> findByTeacherTidAndAcademicyear(int teacherId, String academicYear);

}
