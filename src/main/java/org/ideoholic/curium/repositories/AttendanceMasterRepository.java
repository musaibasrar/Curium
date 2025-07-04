package org.ideoholic.curium.repositories;

import org.ideoholic.curium.model.attendance.dto.Attendancemaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceMasterRepository extends JpaRepository<Attendancemaster, Integer> {

	List<Attendancemaster> findByAttendeeidAndBranchid(String attendeeId, int branchid);

	List<Attendancemaster> findByAttendeeid(String attendeeId);
}
