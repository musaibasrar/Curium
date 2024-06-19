package org.ideoholic.curium.model.appointment.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ideoholic.curium.model.appointment.dto.AddAppointmentDto;
import org.ideoholic.curium.model.appointment.dto.Appointment;
import org.ideoholic.curium.model.appointment.dto.AppointmentResponseDto;
import org.ideoholic.curium.model.appointment.dto.CancelAppointmentsDto;
import org.ideoholic.curium.model.appointment.dto.CompleteAppointmentsDto;
import org.ideoholic.curium.model.appointment.dto.ExportAppointmentsReportDto;
import org.ideoholic.curium.model.appointment.dto.GenerateAppointmentsReportDto;
import org.ideoholic.curium.model.appointment.dto.GenerateAppointmentsReportForClientDto;
import org.ideoholic.curium.model.appointment.dto.MonthlyAppointmentsResponseDto;
import org.ideoholic.curium.model.appointment.dto.UpdateAppointmentDto;
import org.ideoholic.curium.model.appointment.dto.ViewAllAppoinmentsResponseDto;
import org.ideoholic.curium.model.appointment.dto.ViewAllAppointmentsDto;
import org.ideoholic.curium.model.appointment.service.AppointmentService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentActionAdapter {
    
	@Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AppointmentService appointmentService;
    
    private String BRANCHID = "branchid";
    private String CURRENTACADEMICYEAR = "currentacadamicyear";


    public void generateAppointmentsReport() {
        GenerateAppointmentsReportDto generateAppointmentsReportDto = new GenerateAppointmentsReportDto();
        generateAppointmentsReportDto.setFromDate(request.getParameter("transactiondatefrom"));
        generateAppointmentsReportDto.setToDate(request.getParameter("transactiondateto"));
        generateAppointmentsReportDto.setStatus(request.getParameter("status"));
        generateAppointmentsReportDto.setStudentId(request.getParameter("studentId"));
        generateAppointmentsReportDto.setAdmnno(request.getParameter("clientname"));
        generateAppointmentsReportDto.setStudentName(request.getParameter("studentName"));

        AppointmentResponseDto appointmentResponseDto = appointmentService.generateAppointmentsReport(generateAppointmentsReportDto);
        
        httpSession.setAttribute("statusselected", "Status:&nbsp;" + appointmentResponseDto.getStatusselected());
        httpSession.setAttribute("studentselected", "Student Name:&nbsp;" + appointmentResponseDto.getStudentselected());
        httpSession.setAttribute("appointmentList", appointmentResponseDto.getAppointmentList());
        httpSession.setAttribute("transactionfromdateselected", "From:" + appointmentResponseDto.getTransactionfromdateselected());
        httpSession.setAttribute("transactiontodateselected", "To:" + appointmentResponseDto.getTransactiontodateselected());
    }

    public boolean addAppointment() {
        AddAppointmentDto addAppointmentDto = new AddAppointmentDto();
        addAppointmentDto.setStudentId(request.getParameterValues("studentIDs"));
        addAppointmentDto.setAppointmentDate(request.getParameter("appointmentdate"));
        addAppointmentDto.setAppointmentTime(request.getParameter("appointmenttime"));
        
        ResultResponse resultResponse = appointmentService.addAppointment(addAppointmentDto, httpSession.getAttribute(BRANCHID).toString(), httpSession.getAttribute(CURRENTACADEMICYEAR).toString(), httpSession.getAttribute("userloginid").toString());
        
        request.setAttribute("appointmentresult", resultResponse.isSuccess());
        
        return resultResponse.isSuccess();
    }

    public boolean viewAllAppointments() {
        ViewAllAppointmentsDto viewAllAppointmentsDto = ViewAllAppointmentsDto.builder()
        		.page(Integer.parseInt(request.getParameter("page")))
        		.build();

        ViewAllAppoinmentsResponseDto viewAllAppoinmentsResponseDto = appointmentService.viewAllAppointments(viewAllAppointmentsDto, httpSession.getAttribute(BRANCHID).toString());
        
        request.setAttribute("studentList", viewAllAppoinmentsResponseDto.getStudentList());
        request.setAttribute("appointmentList", viewAllAppoinmentsResponseDto.getAppointmentList());
        request.setAttribute("noOfPages", viewAllAppoinmentsResponseDto.getNoOfPages());
        request.setAttribute("currentPage", viewAllAppoinmentsResponseDto.getCurrentPage());
        
        return viewAllAppoinmentsResponseDto.isSuccess();
    }

    public boolean completeAppointments() {
        CompleteAppointmentsDto completeAppointmentsDto = new CompleteAppointmentsDto();
        completeAppointmentsDto.setAppointmentIds(request.getParameterValues("appointmentids"));

        ResultResponse resultResponse = appointmentService.completeAppointments(completeAppointmentsDto);
        
        request.setAttribute("appointmentstatus", resultResponse.isSuccess());
        
        return resultResponse.isSuccess();
    }

    public boolean cancelAppointments() {
        CancelAppointmentsDto cancelAppointmentsDto = new CancelAppointmentsDto();
        cancelAppointmentsDto.setAppointmentIds(request.getParameterValues("appointmentids"));

        ResultResponse resultResponse = appointmentService.cancelAppointments(cancelAppointmentsDto);
        
        request.setAttribute("appointmentstatus", resultResponse.isSuccess());
        
        return resultResponse.isSuccess();
    }

    public void getMonthlyAppointmnents() {
        MonthlyAppointmentsResponseDto monthlyAppointmentsResponseDto = appointmentService.getMonthlyAppointments();
        
        request.setAttribute("monthlytotalappointments", monthlyAppointmentsResponseDto.getMonthlytotalappointments());
        request.setAttribute("monthlistappointment", monthlyAppointmentsResponseDto.getMonthlistappointment());
    }

    public boolean updateAppointment() {
        UpdateAppointmentDto updateAppointmentDto = new UpdateAppointmentDto();
        updateAppointmentDto.setAppointmentIds(request.getParameterValues("appointmentids"));
        updateAppointmentDto.setStarttime(request.getParameter("starttime_"));
        updateAppointmentDto.setEndtime(request.getParameter("endtime_"));

        ResultResponse resultResponse = appointmentService.updateAppointment(updateAppointmentDto);
        
        request.setAttribute("appointmentstatus", resultResponse.isSuccess());
        
        return resultResponse.isSuccess();
    }

    public void generateAppointmentsReportForClient() {
        GenerateAppointmentsReportForClientDto generateAppointmentsReportForClientDto = new GenerateAppointmentsReportForClientDto();
        generateAppointmentsReportForClientDto.setStudentId(request.getParameter("id"));

        ResultResponse resultResponse = appointmentService.generateAppointmentsReportForClient(generateAppointmentsReportForClientDto);
        
        httpSession.setAttribute("appointmentList", resultResponse.getResultList());
    }

    public boolean exportAppointmentsReport() {

        ExportAppointmentsReportDto exportAppointmentsReportDto = new ExportAppointmentsReportDto();
        exportAppointmentsReportDto.setAppoitmentList((List<Appointment>) httpSession.getAttribute("appointmentList"));

        ResultResponse resultResponse = appointmentService.exportAppointmentsReport(exportAppointmentsReportDto);

        return resultResponse.isSuccess();
    }

	public boolean download() {
		return appointmentService.download().isSuccess();
	}
}
