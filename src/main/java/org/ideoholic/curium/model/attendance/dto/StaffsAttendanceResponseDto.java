package org.ideoholic.curium.model.attendance.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class StaffsAttendanceResponseDto {
    private boolean staffsAttendance;
    @Builder.Default
    private boolean success = false;
}
