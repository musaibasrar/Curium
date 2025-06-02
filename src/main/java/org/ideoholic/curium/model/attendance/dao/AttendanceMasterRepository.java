package org.ideoholic.curium.model.attendance.dao;

import org.ideoholic.curium.model.attendance.dto.Attendancemaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceMasterRepository extends JpaRepository<Attendancemaster, Integer> {
}
