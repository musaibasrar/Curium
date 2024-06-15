package org.ideoholic.curium.model.attendance.dto;

import lombok.Data;

@Data
public class HolidaysDto {
    private String[] fromDate;
    private String[] toDate;
    private String[] holidayName;
}
