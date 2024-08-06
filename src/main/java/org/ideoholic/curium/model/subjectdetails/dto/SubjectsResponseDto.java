package org.ideoholic.curium.model.subjectdetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SubjectsResponseDto {
    private List<Subject> list;
    private boolean success;
}
