package org.ideoholic.curium.model.user.service;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Leavedetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDetailsRepository extends JpaRepository<Leavedetails, Long> {
    List<Leavedetails> findByTeacherIdAndAcademicYear(String teacherId, String academicYear);
}
