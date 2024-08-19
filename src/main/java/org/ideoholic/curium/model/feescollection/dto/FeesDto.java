package org.ideoholic.curium.model.feescollection.dto;

import lombok.Data;

import java.util.List;

@Data
public class FeesDto {
    private List<Studentotherfeesreport> studentotherfeesreportList;
    private List<StudentFeesReport> studentFeesReportList;
}
