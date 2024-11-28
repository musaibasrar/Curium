package org.ideoholic.curium.model.periods.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TimeTableViewResponseDto {
    private Periodmaster periodMaster;
    private List<Perioddetails> periodD;
    private Map<String,List<Perioddetails>> periodMap;
    private String periodMasterId;
    @Builder.Default
    private boolean success = false;
}
