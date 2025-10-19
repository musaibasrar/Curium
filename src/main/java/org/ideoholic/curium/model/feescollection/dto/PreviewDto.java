package org.ideoholic.curium.model.feescollection.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreviewDto {
    private Long sid;
    private Integer id;
    private Long idFees;
}