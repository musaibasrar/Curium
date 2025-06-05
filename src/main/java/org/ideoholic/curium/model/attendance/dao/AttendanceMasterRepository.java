package org.ideoholic.curium.model.attendance.dao;

import org.ideoholic.curium.model.attendance.dto.Attendancemaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceMasterRepository extends JpaRepository<Attendancemaster, Integer> {

    List<Attendancemaster> findByAttendeeidBranchid(String attendeeId, int branchid);
}
