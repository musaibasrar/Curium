package org.ideoholic.curium.model.ratingdetails.dto;

import java.util.List;

import org.ideoholic.curium.model.assessmentdetails.dto.HolisticAssessment;

import lombok.Data;

@Data
public class StudentRatingGraphDto {
    private String[] studentIds;
    private String[] assessmentClass;
    private List<HolisticAssessment> assessmentsList;
}
