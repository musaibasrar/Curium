package org.ideoholic.curium.model.subjectdetails.dto;

import lombok.Data;

@Data
public class AddSubjectDto {
    private String subjectname;
    private String minMarks;
    private String maxMarks;
    private String examName;
    private String examClass;

}
