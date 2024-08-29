package org.ideoholic.curium.model.examdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ExamsScheduleResponseDto {
    private List<Examschedule> exams;
    private boolean success;
    private String selectedclass;
    private String selectedexam;
    private String selectedstudentname;
    private String selectedclassandsec;
    private String selectedadmissionno;
    private List<Examschedule> examschedules;
}
