package org.ideoholic.curium.model.attendance.dto;

import java.util.List;

import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentAttendanceDetailsResponseDto {
    private List<Parents> studentListAttendance;
    private List<Studentdailyattendance> studentDailyAttendanceDate;
    private String searchDate;
    private List<Student> studentList;
    private boolean success;
}
