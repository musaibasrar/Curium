package org.ideoholic.curium.model.subjectdetails.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SubSubjectsResponseDto {
    private List<SubSubject> list;
    private Map<String, List<String>> subSubjectMap;
    private boolean success;
}
