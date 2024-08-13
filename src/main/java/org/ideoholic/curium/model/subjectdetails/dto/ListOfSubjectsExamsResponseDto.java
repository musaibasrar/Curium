package org.ideoholic.curium.model.subjectdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.ideoholic.curium.model.examdetails.dto.Exams;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ListOfSubjectsExamsResponseDto {
    private List<Subject> list;
    private boolean success;

    private List<Exams> exams;

    private String message;
    private Map resultMap;
    private Integer resultValue;
    private List resultList;

}
