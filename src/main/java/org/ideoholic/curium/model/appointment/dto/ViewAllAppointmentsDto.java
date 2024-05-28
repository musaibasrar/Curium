package org.ideoholic.curium.model.appointment.dto;

import lombok.Data;

import java.util.List;

@Data
public class ViewAllAppointmentsDto {
private Integer branchid;
private int page;
private int recordsPerPage;
private List<Appointment> studentList;
private List<Appointment> appointmentList;
private int noOfPages;
private int currentPage;
private List<Appointment> list;
}
