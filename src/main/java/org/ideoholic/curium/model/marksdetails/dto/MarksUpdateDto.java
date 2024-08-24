package org.ideoholic.curium.model.marksdetails.dto;

import lombok.Data;

@Data
public class MarksUpdateDto {
    private String[] studentIds;
    private String[] studentsMarks;
    private String[] marksId;
    private String exam;
    private String subject;
}
