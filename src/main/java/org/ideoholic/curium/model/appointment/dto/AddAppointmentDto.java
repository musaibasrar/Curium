package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AddAppointmentDto {
    private String[] studentId;
    private String appointmentDate;
    private String appointmentTime;
    private Integer branchId;
    private String currentAcademicYear;
    private String userloginid;

}