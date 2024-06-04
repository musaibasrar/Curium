package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
@Data
public class GetMonthlyAppointmentsResponseDto {
    private List<String> monthlytotalappointments;
    private List<String> monthlistappointment;
}
