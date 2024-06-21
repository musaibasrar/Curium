package org.ideoholic.curium.model.appointment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ViewAllAppointmentsDto {
    private int page;
}
