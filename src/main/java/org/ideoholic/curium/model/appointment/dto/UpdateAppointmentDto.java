package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

@Data
public class UpdateAppointmentDto {
private String[] appointmentIds;
private String starttime_;
private String endtime;
}
