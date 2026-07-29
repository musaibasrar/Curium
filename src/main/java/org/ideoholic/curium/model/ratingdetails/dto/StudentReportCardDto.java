package org.ideoholic.curium.model.ratingdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;

/**
 * DTO for complete student assessment progress report
 * Contains all data needed for rendering a single student's assessment report card
 * Used in Assessment Progress Report
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StudentReportCardDto {
    // Student information
    private Integer studentId;
    private Parents studentInfo;          // Student name, roll number, class, section
    
    // Assessment context
    private String assessmentName;
    private String academicYear;
    private String assessmentPeriod;      // e.g., "Q1 2024-2025"
    
    // Category-wise summary (dynamically grouped)
    private List<CategorySummaryDto> categorySummaries;
    
    // Overall summary
    private OverallSummaryDto overallSummary;
    
    // Metadata for rendering
    private String reportGeneratedDate;
    private String reportGeneratedBy;     // Teacher/Principal name
    private Integer totalDaysInPeriod;
    private Integer totalClassesAttended;
}
