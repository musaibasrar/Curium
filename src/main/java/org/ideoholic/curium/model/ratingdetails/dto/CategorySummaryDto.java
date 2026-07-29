package org.ideoholic.curium.model.ratingdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for category-level assessment summary
 * Groups subjects by their assessment category
 * Used in Assessment Progress Report
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class CategorySummaryDto {
    private String categoryName;          // Reading Skills, Writing Skills, etc.
    private List<SubjectRatingDto> subjects;  // Subjects within this category
    private Double averageScore;          // Average rating across all subjects in category
    private String categoryGrade;         // Grade based on average (A+, A, B+, etc.)
    private String categoryObservation;   // Observation/remark for entire category
    private Integer totalSubjects;
    private Integer subjectsAttempted;
}
