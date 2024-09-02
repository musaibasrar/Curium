package org.ideoholic.curium.model.examdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ExamsScheduleResponseDto {
    private  List<Exams> exams;
    private List<Subject> list;
    private boolean success;
    private String selectedclass;
    private String selectedexam;
    private String selectedstudentname;
    private String selectedclassandsec;
    private String selectedadmissionno;
    private List<Examschedule> examschedules;

    private String message;
    private Map resultMap;
    private Integer resultValue;
    private List resultList;

    private String currentacademicyear;
}

