package org.ideoholic.curium.model.feescollection.dto;

import lombok.Data;

@Data
public class FeesReportDto {
    private String academicYear;
    private String[] feesCat;
    private String[] addClass;
}
