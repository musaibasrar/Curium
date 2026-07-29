package org.ideoholic.curium.model.ratingdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.util.AssessmentSheet;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GenerateAssessmentReportResponseDto {
    private int endLoop;
    private List<AssessmentSheet> assessmentSheetList;
    @Builder.Default
    private boolean success = false;
    private int totalDays;
    private int totalpresent;
    private int totalabsent;
    private String assessmentName;
    private String startDate;
    private String minRating;
    private String maxRating;
}
