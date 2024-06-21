package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

@Data
public class AddAppointmentDto {
    private String[] studentId;
    private String appointmentDate;
    private String appointmentTime;

}
