package org.ideoholic.curium.model.marksdetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamSummary {
    private String examName;
    private int examId;
    private int totalMarks;
    private float totalMarksObtained;
    private double percentage;
    private String grade;
    private int rank;
}