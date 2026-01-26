package org.ideoholic.curium.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.attendance.dto.Studentdailyattendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDailyAttendanceRepository extends JpaRepository<Studentdailyattendance,Integer> {

	Optional<Studentdailyattendance> findByAttendeeStudentexternalidAndDateAndAcademicyear(String attendeeid, Date date, String academicyear);

    List<Studentdailyattendance> findByDateAndAcademicyearAndAttendeeStudentexternalidAndBranchid(Date date, String academicYear, String attendeeId, int branchId);
    
    List<Studentdailyattendance> findByDateBetweenAndAcademicyearAndAttendeeStudentexternalidAndBranchid(
            Date fromTimestamp,
            Date toTimestamp,
            String currentAcademicYear,
            String studentExternalId,
            Integer branchId);


    @Modifying
    @Query("UPDATE Studentdailyattendance s SET s.attendancestatus = :status WHERE s.attendanceid = :id")
   void updateAttendanceStatusById(@Param("id") Integer id, @Param("status") String status);

    List<Studentdailyattendance> findByDate(String date);

    List<Studentdailyattendance> findByDateAndAttendeeStudentexternalidIn(String date, List<String> attendeeIds);

    @Query("SELECT s FROM Studentdailyattendance s WHERE s.attendee.studentexternalid = :userName AND s.date = :date")
    Studentdailyattendance findByAttendeeIdAndDate(@Param("userName") String userName, @Param("date") Date date);

    @Query("SELECT s FROM Studentdailyattendance s WHERE date <= :datePresent AND academicyear = :currentAcademicYear AND attendee.studentexternalid = :studentExternalId AND branchid = :branchId")
	List<Studentdailyattendance> fetchStudentTotalAttendaceDateWise(
			@Param("datePresent")Date datePresent,
			@Param("currentAcademicYear")String currentAcademicYear, 
			@Param("studentExternalId")String studentExternalId, 
			@Param("branchId")int branchId);
    
    List<Studentdailyattendance> findByAttendee_studentexternalidAndAcademicyearAndDateBetween(String attendeeId, String academicYear,String startDate, String endDate);
}