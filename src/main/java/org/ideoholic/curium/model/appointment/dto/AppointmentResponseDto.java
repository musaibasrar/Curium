package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.List;
@Data
public class AppointmentResponseDto {
private String statusselected;
    private String studentselected;
    private List<Appointment> appointmentList;
    private String transactionfromdateselected;
    private String transactiontodateselected;
}
