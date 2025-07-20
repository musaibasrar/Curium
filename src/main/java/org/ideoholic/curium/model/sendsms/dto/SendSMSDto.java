package org.ideoholic.curium.model.sendsms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ideoholic.curium.model.feescollection.dto.StudentFeesReport;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendSMSDto {
    private String numbers;
    private String messageBodyNumbers;
    private String department;
    private String messageBodyStaff;

    private List<StudentFeesReport> studentFeesReportList;

    private String addClass;
    private String addSec;
    private String smsTempType;
    private String message;
    private int branchId;
    private String[] studentIds;

}
