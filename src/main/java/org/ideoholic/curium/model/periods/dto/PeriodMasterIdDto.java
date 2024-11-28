package org.ideoholic.curium.model.periods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodMasterIdDto {
    private String[] periodMasterId;
}
