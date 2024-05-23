package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

@Data
public class GenerateAppointmentsReportDto {
    private String fromDate;
    private String toDate;
    private String status;
    private String studentId;
    private String admnno;
    private String studentName;
    private String queryMain;
    private String subQuery;
}
