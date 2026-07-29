package org.ideoholic.curium.model.ratingdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for individual subject ratings within a category
 * Used in Assessment Progress Report
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SubjectRatingDto {
    private Integer subjectId;
    private String subjectName;
    private String ratingGrade;           // A+, A, B+, B, C, D, F
    private Integer ratingValue;          // Numeric rating (0-100)
    private String displayScore;          // Formatted as "75/100" or similar
    private String remark;                // Subject-level teacher remark
    private Integer minMarks;
    private Integer maxMarks;
    private Double percentage;            // Calculated as (ratingValue / maxMarks) * 100
}
