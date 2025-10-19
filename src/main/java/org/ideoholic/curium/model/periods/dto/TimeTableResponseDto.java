package org.ideoholic.curium.model.periods.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.employee.dto.Teacher;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TimeTableResponseDto {
    private String  currentYear;
    private List<Periodmaster> periodMaster;
    private List<Subject> subjects;
    private List<Classsec> classSecs;
    private List<Teacher> employeeList;

    private boolean success;
}