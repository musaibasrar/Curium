package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.List;
@Data
public class MonthlyAppointmentsResponseDto {
    private List<String> monthlytotalappointments;
    private List<String> monthlistappointment;
}
