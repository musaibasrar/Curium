package org.ideoholic.curium.model.periods.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeriodsSaveDto {
    private String academicYear;
    private String totalNoOfPeriods;
    private String durationOfPeriodsHr;
    private String durationOfPeriodsMin;
    private String dayStartTimeHr;
    private String dayStartTimeMin;
    private String dayStartAm;
    private String dayEndTimeHr;
    private String dayEndTimeMin;
    private String dayEndAm;
    private String periodMasterId;
    private String fromClass;
    private String toClass;

    private String[] periods;
    private String[] periodId;
    private String[] subjects;
    private String[] staff;
    private String[] periodStartTimeHr;
    private String[] periodStartTimeMin;
    private String[] periodStartTimeAm;
    private String[] periodEndTimeHr;
    private String[] periodEndTimeMin;
    private String[] periodEndTimeAm;
    private String[] days;
}
