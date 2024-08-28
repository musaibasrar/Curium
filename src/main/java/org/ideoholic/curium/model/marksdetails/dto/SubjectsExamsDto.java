package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.std.dto.Classsec;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SubjectsExamsDto {
    private List<Subject> subjectList;
    private List<Exams> examList;
    private List<Classsec> classsecList;
}
