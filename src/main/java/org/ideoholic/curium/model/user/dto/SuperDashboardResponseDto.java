package org.ideoholic.curium.model.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class SuperDashboardResponseDto {
    @Builder.Default
    private boolean success = false;
    private String message;
    private String generatedAt;
    private Map<String, Object> summary;
    private Map<String, Object> comparison;
    private List<Map<String, Object>> branches;
    private List<Map<String, Object>> ranking;
    private Map<String, Object> charts;
    private Map<String, Object> filterOptions;
}
