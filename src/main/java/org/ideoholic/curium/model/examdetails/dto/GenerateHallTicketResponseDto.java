package org.ideoholic.curium.model.examdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class GenerateHallTicketResponseDto {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cayid", unique = true, nullable = false)
    private Integer cayid;

    @Column(name = "currentacademicyear", length = 100)
    private String currentacademicyear;
    private List<Examschedule> exams;
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



}
