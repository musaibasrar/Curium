package org.ideoholic.attendance;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.attendance.dto.AttendanceProfileDto;
import org.ideoholic.attendance.service.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("AttendanceProcess")
@Component
@Scope("singleton")

public class AttendanceRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(AttendanceRestApiResource.class);
	
	@Inject
	private AttendanceService attendanceService;
	
	@POST
	@Path("viewAllHolidays")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAllHolidays(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.viewAllHolidays(attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addHolidays")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addHolidays(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.addHolidays(attendanceDto.getFromDate(),attendanceDto.getToDate(),attendanceDto.getHolidayName(),
				attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addWeekOff")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addWeekOff(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.addWeekOff(attendanceDto.getWeekOff(),attendanceDto.getCurrentAcademicYear(),
				attendanceDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.deleteMultiple(attendanceDto.getIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addStudentAttendanceMaster")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addStudentAttendanceMaster(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.addStudentAttendanceMaster(attendanceDto.getWeeklyOff(),attendanceDto.getHolidays(),
				attendanceDto.getBranchId(),attendanceDto.getCutOff());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addStaffAttendanceMaster")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addStaffAttendanceMaster(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.addStaffAttendanceMaster(attendanceDto.getStaffId(),attendanceDto.getWeekOff(),
				attendanceDto.getHolidays(),attendanceDto.getInTime(),attendanceDto.getOutTime(),attendanceDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("uploadAttendanceFile")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response uploadAttendanceFile(final AttendanceProfileDto attendanceDto) throws IOException{
		String output = "";
		output =attendanceService.uploadAttendanceFile(attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	
	@POST
	@Path("searchStudentAttendanceDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchStudentAttendanceDetails(final AttendanceProfileDto attendanceDto) throws IOException{
		String output = "";
		output =attendanceService.searchStudentAttendanceDetails(attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId(),
				attendanceDto.getStudentName(),attendanceDto.getAddClass(),attendanceDto.getAddSec(),
				attendanceDto.getDateOfAttendance());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewAttendance")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAttendance(final AttendanceProfileDto attendanceDto) throws IOException{
		String output = "";
		output =attendanceService.viewAttendance(attendanceDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("updateStudentAttendanceDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateStudentAttendanceDetails(final AttendanceProfileDto attendanceDto) throws IOException{
		String output = "";
		output =attendanceService.updateStudentAttendanceDetails(attendanceDto.getCurrentAcademicYear(),
				attendanceDto.getAttendanceIds(),attendanceDto.getStudentAttendanceStatus());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("markStudentsAttendance")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response markStudentsAttendance(final AttendanceProfileDto attendanceDto) throws IOException{
		String output = "";
		output =attendanceService.markStudentsAttendance(attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId(),
				attendanceDto.getAttendanceIds(),attendanceDto.getStudentAttendanceStatus(),attendanceDto.getAttendanceClass());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("exportMonthlyData")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportMonthlyData(final AttendanceProfileDto attendanceDto) throws IOException{
		String output = "";
		output =attendanceService.exportMonthlyData(attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId(),
				attendanceDto.getAddClass(),attendanceDto.getAddSec(),attendanceDto.getMonthOf());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("downloadFile")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response downloadFile(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.downloadFile(attendanceDto.getBuffer());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewAttendanceStaff")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAttendanceStaff(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.viewAttendanceStaff(attendanceDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("searchStaffAttendanceDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchStaffAttendanceDetails(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.searchStaffAttendanceDetails(attendanceDto.getCurrentAcademicYear(),
				attendanceDto.getBranchId(),attendanceDto.getDateOfAttendance());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("updateStaffAttendanceDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateStaffAttendanceDetails(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.updateStaffAttendanceDetails(attendanceDto.getCurrentAcademicYear(),
				attendanceDto.getAttendanceIds(),attendanceDto.getStudentAttendanceStatus());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewStaffAttendanceDetailsMonthly")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewStaffAttendanceDetailsMonthly(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.viewStaffAttendanceDetailsMonthly(attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId(),
				attendanceDto.getStaffExternalId(),attendanceDto.getFromdateofattendance(),attendanceDto.getTodateofattendance(),
				attendanceDto.getNameofstaff());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("markStaffAttendance")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response markStaffAttendance(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.markStaffAttendance(attendanceDto.getCurrentAcademicYear(),attendanceDto.getBranchId(),
				attendanceDto.getAttendanceIds(),attendanceDto.getStaffAttendanceStatus(),attendanceDto.getInTime(),
				attendanceDto.getOutTime());
		return Response.status(200).entity(output).build();

	}

	
	@POST
	@Path("exportMonthlyDataStaff")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportMonthlyDataStaff(final AttendanceProfileDto attendanceDto){
		String output = "";
		output =attendanceService.exportMonthlyDataStaff(attendanceDto.getCurrentAcademicYear(),
				attendanceDto.getBranchId(),attendanceDto.getMonthOf());
		return Response.status(200).entity(output).build();

	}
}
