package org.ideoholic.curium.model.ratingdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.parents.dto.Parents;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class RatingDto {
    // Legacy fields (for backward compatibility)
    private List<HolisticRating> ratingList;
    private List<Integer> studentsIds;
    
    // New fields (aligned with MarksResponseDto pattern)
    private List<Parents> newStudentList;
    private List<HolisticRating> newRatingsList;
    private String subjectSelected;
    private String assessmentSelected;
    private String subject;
    private String assessment;
    private String classSelected;
    private String sectionSelected;
    private String studentName;
    
    @Builder.Default
    private boolean success = false;
}
