package org.ideoholic.curium.model.periods.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class TimeTableResponseDto {
    private String  currentYear;
    private List<Periodmaster> periodMaster;
    private boolean success;
}
