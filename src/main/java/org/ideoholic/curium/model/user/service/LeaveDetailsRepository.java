package org.ideoholic.curium.model.user.service;

import java.util.List;

import org.ideoholic.curium.model.hr.dto.Leavedetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDetailsRepository extends JpaRepository<Leavedetails, Integer> {
    List<Leavedetails> findByTeacherTidAndAcademicyear(String teacherId, String academicYear);
}
