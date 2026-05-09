package org.ideoholic.curium.model.marksdetails.dto;

import java.util.List;

import org.ideoholic.curium.model.examdetails.dto.Exams;

import lombok.Data;

@Data
public class StudentGraphDto {
    private String[] studentIds;
    private String[] examClass;
    private List<Exams> examsList;
}
