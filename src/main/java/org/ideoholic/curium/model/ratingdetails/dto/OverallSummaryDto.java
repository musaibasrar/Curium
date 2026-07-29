package org.ideoholic.curium.model.ratingdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for overall student assessment summary
 * Aggregated data across all categories and subjects
 * Used in Assessment Progress Report
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class OverallSummaryDto {
    private String overallGrade;          // Overall letter grade (A+, A, B+, etc.)
    private Double overallPercentage;     // Overall numeric score (0-100)
    private Double averageAcrossCategories;  // Average score across all categories
    private Integer totalRatedSubjects;
    private Integer totalCategories;
    
    // Aggregate remarks - top N remarks from lowest-performing categories
    private String strengths;             // Concatenated strengths (top N remarks)
    private String improvements;          // Concatenated improvement areas
    private String teacherOverallRemarks; // Overall teacher remarks
}
