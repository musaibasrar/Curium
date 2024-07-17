/**
 * 
 */
package org.ideoholic.curium.model.appointment.action;

import org.ideoholic.curium.dto.ResultResponse;
import org.ideoholic.curium.exceptions.CustomErrorMessage;
import org.ideoholic.curium.exceptions.CustomResponseException;
import org.ideoholic.curium.model.appointment.dto.*;
import org.ideoholic.curium.model.appointment.service.AppointmentService;
import org.ideoholic.curium.model.employee.action.EmployeeActionAdapter;
import org.ideoholic.curium.model.std.action.StandardActionAdapter;
import org.ideoholic.curium.model.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/v1/appointmentProcess")
public class AppointmentApiAction {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private EmployeeActionAdapter employeeActionAdapter;
	@Autowired
	private StandardActionAdapter standardActionAdapter;

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
		new StudentService(request, response, standardActionAdapter).viewAllStudentsList();
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
			new StudentService(request, response, standardActionAdapter).viewAllStudentsParents();
			employeeActionAdapter.ViewAllEmployee();
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
