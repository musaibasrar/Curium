package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class AddHolidaysDto {
    private String[] fromDate;
    private String[] toDate;
    private String[] holidayName;
    private String currentAcademicYear;
    private String  branchId;
}
