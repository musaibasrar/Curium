package org.ideoholic.curium.model.periods.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TeacherTimeTableResponseDto {
    private String teacherName;
    private List<Map<String, String>> periodMapList;
    private boolean success;
}