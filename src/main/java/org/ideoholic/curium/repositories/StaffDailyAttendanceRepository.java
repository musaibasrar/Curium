package org.ideoholic.curium.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.ideoholic.curium.model.attendance.dto.Staffdailyattendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDailyAttendanceRepository extends JpaRepository<Staffdailyattendance, Integer> {

    Optional<Staffdailyattendance> findByAttendeeidAndDateAndAcademicyear(String attendeeid, Date date, String academicyear);

    List<Staffdailyattendance> findByDateAndAcademicyearAndAttendeeidAndBranchid(Date date, String academicyear, String attendeeid, int branchid);

    List<Staffdailyattendance> findByDateBetweenAndAcademicyearAndAttendeeidAndBranchid(Date fromDate, Date toDate, String academicyear, String attendeeid, int branchid);

    @Modifying
    @Query("UPDATE Staffdailyattendance s SET s.attendancestatus = :status WHERE s.attendanceid = :id")
    void updateAttendanceStatusById(@Param("id") Integer id, @Param("status") String status);

}
