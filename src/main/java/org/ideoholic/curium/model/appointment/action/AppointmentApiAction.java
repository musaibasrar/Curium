/**
 * 
 */
package org.ideoholic.curium.model.appointment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.appointment.dto.AddAppointmentDto;
import org.ideoholic.curium.model.appointment.dto.CancelAppointmentsDto;
import org.ideoholic.curium.model.appointment.dto.CompleteAppointmentsDto;
import org.ideoholic.curium.model.appointment.dto.ExportAppointmentsReportDto;
import org.ideoholic.curium.model.appointment.dto.GenerateAppointmentsReportDto;
import org.ideoholic.curium.model.appointment.dto.UpdateAppointmentDto;
import org.ideoholic.curium.model.appointment.dto.ViewAllAppoinmentsResponseDto;
import org.ideoholic.curium.model.appointment.dto.ViewAllAppointmentsDto;
import org.ideoholic.curium.model.appointment.service.AppointmentService;
import org.ideoholic.curium.model.employee.service.EmployeeService;
import org.ideoholic.curium.model.student.service.StudentService;
import org.ideoholic.curium.util.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/v1/appointmentProcess")
public class AppointmentApiAction {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/download")
	private ResponseEntity<ResultResponse> download() {
		ResultResponse result = appointmentService.download();
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
		}
	}

	@PostMapping("/exportAppointmentsReport")
	private ResponseEntity<ResultResponse> exportAppointmentsReport(@RequestBody ExportAppointmentsReportDto exportDto) {
		ResultResponse result = appointmentService.exportAppointmentsReport(exportDto);
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.EXPORTFAILURE);
		}
	}

	@PostMapping("/printAppointmentsReport")
	private String printAppointmentsReport() {
		return "printappointmentsreport";
	}
	
	@PostMapping("/generateAppointmentsReport")
	private String generateAppointmentsReport(@RequestBody GenerateAppointmentsReportDto appointmentsReport) {
		appointmentService.generateAppointmentsReport(appointmentsReport);
		return appointmentReport();
	}

	@GetMapping("/appointmentReport")
	private String appointmentReport() {
		// TODO: Need to fix this after migrating StudentService
		new StudentService(request, response).viewAllStudentsList();
		return "appointmentsreport";
	}

	@PostMapping("/cancelAppointments")
	private ResponseEntity<ViewAllAppoinmentsResponseDto> cancelAppointments(@RequestBody CancelAppointmentsDto cancelAppointmentsDto, @RequestHeader(value="branchId") String branchId) {
		appointmentService.cancelAppointments(cancelAppointmentsDto);
		return viewAllAppointments(ViewAllAppointmentsDto.builder().page(0).build(), branchId);
	}

	@PostMapping("/completeAppointments")
	private ResponseEntity<ViewAllAppoinmentsResponseDto> completeAppointments(@RequestBody CompleteAppointmentsDto completeAppointmentsDto, @RequestHeader(value="branchId") String branchId) {
		appointmentService.completeAppointments(completeAppointmentsDto);
		return viewAllAppointments(ViewAllAppointmentsDto.builder().page(0).build(), branchId);
	}

	@RequestMapping(value = "/viewAllAppointments", method = { RequestMethod.GET, RequestMethod.POST })
	private ResponseEntity<ViewAllAppoinmentsResponseDto> viewAllAppointments(@RequestBody ViewAllAppointmentsDto viewAllAppointmentsDto, @RequestHeader(value="branchId") String branchId) {
		ViewAllAppoinmentsResponseDto result = appointmentService.viewAllAppointments(viewAllAppointmentsDto, branchId); 
		if (result.isSuccess()) {
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/addAppointment")
	private ResponseEntity<ResultResponse> addAppointment(@RequestBody AddAppointmentDto addAppointmentDto, @RequestHeader(value="branchId") String branchId, @RequestHeader(value="currentAcademicYear") String currentAcademicYear, @RequestHeader(value="userLoginId") String userLoginId) {
		ResultResponse result = appointmentService.addAppointment(addAppointmentDto, branchId, currentAcademicYear, userLoginId);
		if (result.isSuccess()) {
			// TODO: Need to fix this after migrating StudentService and EmployeeService
			new StudentService(request, response).viewAllStudentsParents();
			new EmployeeService(request, response).ViewAllEmployee();
			return ResponseEntity.ok(result);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

	@PostMapping("/updateAppointment")
	private ResponseEntity<ViewAllAppoinmentsResponseDto> updateAppointment(@RequestBody UpdateAppointmentDto updateAppointmentDto, @RequestHeader(value="branchId") String branchId) {
		ResultResponse result = appointmentService.updateAppointment(updateAppointmentDto);
		if (result.isSuccess()) {
			return viewAllAppointments(ViewAllAppointmentsDto.builder().page(0).build(), branchId);
		} else {
			throw new CustomResponseException(CustomErrorMessage.ERROR);
		}
	}

}
