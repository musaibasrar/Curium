package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

@Data
public class CompleteAppointmentsDto {
    private String[] appointmentIds;
    private boolean appointmentstatus;
}
