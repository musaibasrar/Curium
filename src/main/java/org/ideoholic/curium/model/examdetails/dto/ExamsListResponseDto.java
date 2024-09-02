package org.ideoholic.curium.model.examdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ExamsListResponseDto {
    private List<Exams> exams;
    private boolean success;
}
