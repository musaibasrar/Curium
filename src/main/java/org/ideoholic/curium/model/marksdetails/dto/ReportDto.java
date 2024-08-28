package org.ideoholic.curium.model.marksdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.examdetails.dto.Exams;
import org.ideoholic.curium.model.parents.dto.Parents;
import org.ideoholic.curium.model.student.dto.Student;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ReportDto {
    private List<Parents> searchStudentList;
    private List<Subject> subjectList;
    private List<Exams> examList;
    private List<Student> studentList;
}
