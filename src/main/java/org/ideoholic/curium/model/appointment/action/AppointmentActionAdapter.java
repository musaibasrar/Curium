package org.ideoholic.curium.model.appointment.action;

import org.ideoholic.curium.model.appointment.dto.*;
import org.ideoholic.curium.model.appointment.service.AppointmentService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    public boolean addAppointment() {
        AppointmentService appointmentService = new AppointmentService(request, response);

        AddAppointmentDto addAppointmentDto = new AddAppointmentDto();
        addAppointmentDto.setStudentId(request.getParameterValues("studentIDs"));
        addAppointmentDto.setAppointmentDate(request.getParameter("appointmentdate"));
        addAppointmentDto.setAppointmentTime(request.getParameter("appointmenttime"));

        addAppointmentDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));
        addAppointmentDto.setCurrentAcademicYear(httpSession.getAttribute("currentAcademicYear").toString());
        addAppointmentDto.setUserloginid(httpSession.getAttribute("userloginid").toString());
        ResultResponse resultResponse = appointmentService.addAppointment(addAppointmentDto);
        request.setAttribute("appointmentresult", resultResponse.isSuccess());
        return resultResponse.isSuccess();
    }

    public boolean viewAllAppointments() {
        AppointmentService appointmentService= new AppointmentService(request,response);
        ViewAllAppointmentsDto viewAllAppointmentsDto =new ViewAllAppointmentsDto();
        viewAllAppointmentsDto.setPage(Integer.parseInt(request.getParameter("page")));

        viewAllAppointmentsDto.setBranchId(Integer.parseInt(httpSession.getAttribute("branchid").toString()));

        ViewAllAppoinmentsResponseDto viewAllAppoinmentsResponseDto =appointmentService.viewAllAppointments(viewAllAppointmentsDto);
        request.setAttribute("studentList", viewAllAppoinmentsResponseDto.getStudentList());
        request.setAttribute("appointmentList", viewAllAppoinmentsResponseDto.getAppointmentList());
        request.setAttribute("noOfPages", viewAllAppoinmentsResponseDto.getNoOfPages());
        request.setAttribute("currentPage",viewAllAppoinmentsResponseDto.getCurrentPage());
        return true;
    }
   public boolean completeAppointments(){
        AppointmentService appointmentService = new AppointmentService(request,response);
        CompleteAppointmentsDto completeAppointmentsDto = new CompleteAppointmentsDto();
       completeAppointmentsDto.setAppointmentIds(request.getParameterValues("appointmentids"));

       ResultResponse resultResponse = appointmentService.completeAppointments(completeAppointmentsDto);
       request.setAttribute("appointmentstatus",resultResponse.isSuccess());
       return resultResponse.isSuccess();
   }

   public boolean cancelAppointments(){
        AppointmentService appointmentService = new AppointmentService(request,response);
        CancelAppointmentsDto cancelAppointmentsDto = new CancelAppointmentsDto();
        cancelAppointmentsDto.setAppointmentIds(request.getParameterValues("appointmentids"));

        ResultResponse resultResponse =appointmentService.cancelAppointments(cancelAppointmentsDto);
        request.setAttribute("appointmentstatus",resultResponse.isSuccess());
        return resultResponse.isSuccess();
   }
public void getMonthlyAppointmnents(){
        AppointmentService appointmentService = new AppointmentService(request,response);
        MonthlyAppointmentsResponseDto monthlyAppointmentsResponseDto = appointmentService.getMonthlyAppointments();
    request.setAttribute("monthlytotalappointments", monthlyAppointmentsResponseDto.getMonthlytotalappointments());
    request.setAttribute("monthlistappointment", monthlyAppointmentsResponseDto.getMonthlistappointment());
}
public boolean updateAppointment(){
        AppointmentService appointmentService =new AppointmentService(request,response);
        UpdateAppointmentDto updateAppointmentDto = new UpdateAppointmentDto();
        updateAppointmentDto.setAppointmentIds(request.getParameterValues("appointmentids"));
        updateAppointmentDto.setStarttime(request.getParameter("starttime_"));
        updateAppointmentDto.setEndtime(request.getParameter("endtime_"));

        ResultResponse resultResponse = appointmentService.updateAppointment(updateAppointmentDto);
        request.setAttribute("appointmentstatus",resultResponse.isSuccess());
        return resultResponse.isSuccess();
   }

   public void generateAppointmentsReportForClient(){
        AppointmentService appointmentService= new AppointmentService(request,response);
        GenerateAppointmentsReportForClientResponseDto generateAppointmentsReportForClientResponseDto = appointmentService.generateAppointmentsReportForClient();
        httpSession.setAttribute("appointmentList", generateAppointmentsReportForClientResponseDto.getAppointmentList());
   }
}
