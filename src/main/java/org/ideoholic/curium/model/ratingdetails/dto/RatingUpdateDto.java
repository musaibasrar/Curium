package org.ideoholic.curium.model.ratingdetails.dto;

import lombok.Data;

@Data
public class RatingUpdateDto {
    private String[] studentIds;
    private String[] studentsRatings;
    private String[] ratingId;
    private String assessment;
    private String subject;
    private String classSearch;
    private String academicYear;
    private String[] studentsRatingsA1;
    private String[] studentsRatingsA2;
    private String[] studentsRatingsA3;
    private String[] studentsRatingsA4;
}
