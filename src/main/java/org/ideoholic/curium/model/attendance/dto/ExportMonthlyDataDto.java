package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class ExportMonthlyDataDto {
    private String addClass;
    private String addSec;
    private String month;          // 1-12
    private String year;           // 4-digit year (e.g., 2024)
    private String monthOf;        // Deprecated: kept for backward compatibility
}
