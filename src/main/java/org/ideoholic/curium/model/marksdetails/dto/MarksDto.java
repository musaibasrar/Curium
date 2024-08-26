package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class MarksDto {
    private List<Parents> newStudentList;
    private List<Marks> newMarksDetails;
    private String subjectSelected;
    private String examSelected;
    private String subject;
    private String exam;
    private List<Subject> subjectList;
    private List<Exams> examList;
}
