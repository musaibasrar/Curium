package org.ideoholic.curium.model.marksdetails.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectSummary {
    private String subjectName;
    private float minMarks;
    private float maxMarks;
    @Builder.Default
    private List<ExamSummary> examMarks = new ArrayList<>();
    private float totalMarksObtained;
    private float totalMaxMarks;
    private double totalPercentage;
    private String overallGrade;
    
    public void addExamMarks(String examName, float marksObtained, String grade) {
        this.examMarks.add(ExamSummary.builder()
            .examName(examName)
            .totalMarksObtained(marksObtained)
            .grade(grade)
            .build());
        this.totalMarksObtained += marksObtained;
        this.totalMaxMarks += maxMarks;
    }
    
    public void calculateTotals() {
        if (this.totalMaxMarks > 0) {
            this.totalPercentage = (this.totalMarksObtained * 100.0) / this.totalMaxMarks;
        }
    }
}