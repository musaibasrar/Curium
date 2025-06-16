package org.ideoholic.curium.model.attendance.dao;

import org.ideoholic.curium.model.attendance.dto.Studentdailyattendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface StudentDailyAttendanceRepository extends JpaRepository<Studentdailyattendance,Integer> {

    Optional<Studentdailyattendance> findByAttendeeidAndDateAndAcademicyear(String attendeeid, LocalDate date, String academicyear);


    boolean updateAttendanceStatus(String attendancestatus, String attendanceid);
}
