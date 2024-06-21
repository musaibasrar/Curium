package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ViewAllAppoinmentsResponseDto {
    private List<Appointment> studentList;
    private List<Appointment> appointmentList;
    private int noOfPages;
    private int currentPage;
    private boolean success;
}
