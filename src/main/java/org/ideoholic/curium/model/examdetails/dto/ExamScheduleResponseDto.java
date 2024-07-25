package org.ideoholic.curium.model.examdetails.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamScheduleResponseDto {
    private List<Examschedule> exams;
    private boolean success;
}
