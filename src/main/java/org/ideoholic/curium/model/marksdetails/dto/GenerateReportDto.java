package org.ideoholic.curium.model.marksdetails.dto;

import lombok.Data;

@Data
public class GenerateReportDto {
    private String[] studentIds;
    private String examClass;
    private String[] marksIds;
    private String studentUID;
}
