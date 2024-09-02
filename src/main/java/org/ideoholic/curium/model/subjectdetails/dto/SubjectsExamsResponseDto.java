package org.ideoholic.curium.model.subjectdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.examdetails.dto.Examschedule;
import org.ideoholic.curium.model.std.dto.Classsec;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SubjectsExamsResponseDto {
    private List<Subject> subjects;
    private List<Subject> subjectNames;
    private List<Exams> exams;
    private List<Classsec> classsecList;
    private boolean success;

}
