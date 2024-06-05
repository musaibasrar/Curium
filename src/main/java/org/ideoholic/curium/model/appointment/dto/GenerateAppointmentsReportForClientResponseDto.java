package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.List;
@Data
public class GenerateAppointmentsReportForClientResponseDto {
private List<Appointment> appointmentList;
}
