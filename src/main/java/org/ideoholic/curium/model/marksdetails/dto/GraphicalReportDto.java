package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.student.dto.Student;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GraphicalReportDto {
    private List<Student> studentList;
    private List<Classsec> classsecList;
}
