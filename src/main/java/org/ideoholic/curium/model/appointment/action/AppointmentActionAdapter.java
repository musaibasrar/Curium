package org.ideoholic.curium.model.appointment.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.appointment.dto.AppointmentResponseDto;
import org.ideoholic.curium.model.appointment.dto.GenerateAppointmentsReportDto;
import org.ideoholic.curium.model.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AppointmentActionAdapter {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession httpSession;

    public void generateAppointmentsReport() {

        AppointmentService appointmentService = new AppointmentService(request, response);

        GenerateAppointmentsReportDto generateAppointmentsReportDto = new GenerateAppointmentsReportDto();
        generateAppointmentsReportDto.setFromDate(request.getParameter("transactiondatefrom"));
        generateAppointmentsReportDto.setToDate(request.getParameter("transactiondateto"));
        generateAppointmentsReportDto.setStatus(request.getParameter("status"));
        generateAppointmentsReportDto.setStudentId(request.getParameter("studentId"));
        generateAppointmentsReportDto.setAdmnno(request.getParameter("clientname"));
        generateAppointmentsReportDto.setStudentName(request.getParameter("studentName"));

        AppointmentResponseDto appointmentResponseDto= appointmentService.generateAppointmentsReport(generateAppointmentsReportDto);
        httpSession.setAttribute("statusselected", "Status:&nbsp;"+appointmentResponseDto.getStatusselected());
        httpSession.setAttribute("studentselected", "Student Name:&nbsp;"+appointmentResponseDto.getStudentselected());
        httpSession.setAttribute("appointmentList",appointmentResponseDto.getAppointmentList());
        httpSession.setAttribute("transactionfromdateselected", "From:"+ appointmentResponseDto.getTransactionfromdateselected());
        httpSession.setAttribute("transactiontodateselected", "To:"+appointmentResponseDto.getTransactiontodateselected());

    }
}
