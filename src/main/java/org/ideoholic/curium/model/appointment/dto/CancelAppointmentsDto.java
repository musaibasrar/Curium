package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

@Data
public class CancelAppointmentsDto {
    private String[] appointmentIds;
}
