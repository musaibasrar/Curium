package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.List;

@Data
public class AppointmentResponseDto {
    private String statusSelected;
    private String studentSelected;
    private List<Appointment> appointmentList;
    private String transactionFromDateSelected;
    private String transactionToDateSelected;
}
