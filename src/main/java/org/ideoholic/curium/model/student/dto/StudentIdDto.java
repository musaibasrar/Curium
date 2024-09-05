package org.ideoholic.curium.model.student.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentIdDto {
    private String studentId;
    private String academicYear;
}
