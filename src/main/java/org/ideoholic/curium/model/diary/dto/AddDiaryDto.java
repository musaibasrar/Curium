package org.ideoholic.curium.model.diary.dto;

import lombok.Data;

@Data
public class AddDiaryDto {
    private String addSec;
    private String addClass;
    private String messageBody;
    private String subject;
    private String userLoginId;
    private String currentAcademicYear;
    private String createdDate;
    private String endDate;
    private String startDate;
}
