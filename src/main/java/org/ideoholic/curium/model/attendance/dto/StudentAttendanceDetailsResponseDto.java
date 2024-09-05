package org.ideoholic.curium.model.attendance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.student.dto.Student;

import java.util.Date;
import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentAttendanceDetailsResponseDto {
    private List<Student> studentListAttendance;
    private List<Studentdailyattendance> studentDailyAttendanceDate;
    private Date searchDate;
    private List<Student> studentList;
    private boolean success;
}
