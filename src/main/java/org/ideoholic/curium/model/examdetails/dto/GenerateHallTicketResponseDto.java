package org.ideoholic.curium.model.examdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.ideoholic.curium.model.subjectdetails.dto.Subject;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GenerateHallTicketResponseDto {

    private List<Exams> exams;
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
