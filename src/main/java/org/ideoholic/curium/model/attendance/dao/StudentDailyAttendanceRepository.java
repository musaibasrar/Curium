package org.ideoholic.curium.model.attendance.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.attendance.dto.Studentdailyattendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDailyAttendanceRepository extends JpaRepository<Studentdailyattendance,Integer> {

    Optional<Studentdailyattendance> findByAttendee_sidAndDateAndAcademicyear(String attendeeid, LocalDate date, String academicyear);

    List<Studentdailyattendance> findByDateAndAcademicyearAndAttendee_studentexternalidAndBranchid(String date, String academicYear, String attendeeId, int branchId);
}