package org.ideoholic.curium.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.attendance.dto.Studentdailyattendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDailyAttendanceRepository extends JpaRepository<Studentdailyattendance,Integer> {

	Optional<Studentdailyattendance> findByAttendeeStudentexternalidAndDateAndAcademicyear(String attendeeid, LocalDate date, String academicyear);

    List<Studentdailyattendance> findByDateAndAcademicyearAndAttendeeStudentexternalidAndBranchid(String date, String academicYear, String attendeeId, int branchId);
    
    List<Studentdailyattendance> findByDateBetweenAndAcademicyearAndAttendeeStudentexternalidAndBranchid(
            LocalDate fromTimestamp,
            LocalDate toTimestamp,
            String currentAcademicYear,
            String studentExternalId,
            Integer branchId);


    @Modifying
    @Query("UPDATE Studentdailyattendance s SET s.attendancestatus = :status WHERE s.attendanceid = :id")
   void updateAttendanceStatusById(@Param("id") Integer id, @Param("status") String status);

    List<Studentdailyattendance> findByDate(String date);

    List<Studentdailyattendance> findByDateAndAttendeeStudentexternalidIn(String date, List<String> attendeeIds);

    @Query("SELECT s FROM Studentdailyattendance s WHERE s.attendee.studentexternalid = :userName AND s.date = :date")
    Studentdailyattendance findByAttendeeIdAndDate(@Param("userName") String userName, @Param("date") LocalDate date);
}