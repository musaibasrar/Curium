package org.ideoholic.curium.model.position.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class PositionResponseDto {
    private List<Position> positionList;
    @Builder.Default
    private boolean success = false;
}
