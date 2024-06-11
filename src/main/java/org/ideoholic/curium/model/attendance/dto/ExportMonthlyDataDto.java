package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ExportMonthlyDataDto {
    private String addClass;
    private String addSec;
    private Date monthOf;
    private String branchId;
    private String currentAcademicYear;
}
