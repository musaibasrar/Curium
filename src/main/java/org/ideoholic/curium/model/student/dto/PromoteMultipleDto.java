package org.ideoholic.curium.model.student.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PromoteMultipleDto {
    private String[] studentIds;
    private String classStudying;
    private Map<String, String> requestParams;
}
