package org.ideoholic.hr;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.ideoholic.hr.dto.HrProfileDto;
import org.ideoholic.hr.service.HrService;

@Path("HrProcess")
@Component
@Scope("singleton")


public class HrRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(HrRestApiResource.class);
	
	@Inject
	private HrService hrService;
	
	@POST
	@Path("leaveType")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response leaveType(final HrProfileDto hrDto){
		String output = "";
		output =hrService.leaveType(hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("saveLeaveType")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveLeaveType(final HrProfileDto hrDto){
		String output = "";
		output =hrService.saveLeaveType(hrDto.getBranchId(),hrDto.getLeaveTypeName());
		return Response.status(200).entity(output).build();

	}

	
	@POST
	@Path("deleteLeaveType")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteLeaveType(final HrProfileDto hrDto){
		String output = "";
		output =hrService.deleteLeaveType(hrDto.getIdLeave());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addLeaves")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addLeaves(final HrProfileDto hrDto){
		String output = "";
		output =hrService.addLeaves(hrDto.getBranchId(),hrDto.getCurrentAcademicYear(),hrDto.getLeaveTypeName1(),
				hrDto.getTotalLeaves(),hrDto.getStaff());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewLeavesDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewLeavesDetails(final HrProfileDto hrDto){
		String output = "";
		output =hrService.viewLeavesDetails(hrDto.getId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("leaveDetailsPerYear")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response leaveDetailsPerYear(final HrProfileDto hrDto){
		String output = "";
		output =hrService.leaveDetailsPerYear(hrDto.getLeaveDetailsTeacherSid(),hrDto.getAcademicYear());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("payHead")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response payHead(final HrProfileDto hrDto){
		String output = "";
		output =hrService.payHead(hrDto.getCurrentAcademicYear(),hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("savePayHead")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response savePayHead(final HrProfileDto hrDto){
		String output = "";
		output =hrService.savePayHead(hrDto.getCurrentAcademicYear(), hrDto.getPayHeadName(),hrDto.getType(),
				hrDto.getValidatory(),hrDto.getDescription(),hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addPayHeadStaffDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addPayHeadStaffDetails(final HrProfileDto hrDto){
		String output = "";
		output =hrService.addPayHeadStaffDetails(hrDto.getCurrentAcademicYear(),hrDto.getStaff(),hrDto.getValues(),
				hrDto.getPayHeadId(),hrDto.getAmountPerc(),hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addBasicPay")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addBasicPay(final HrProfileDto hrDto){
		String output = "";
		output =hrService.addBasicPay(hrDto.getCurrentAcademicYear(),hrDto.getStaffIds(),hrDto.getBasicPay(),hrDto.getPaymentType(),
				hrDto.getAccountNo(),hrDto.getOverTime(),hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("pfSettings")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response pfSettings(final HrProfileDto hrDto){
		String output = "";
		output =hrService.pfSettings(hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addPf")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addPf(final HrProfileDto hrDto){
		String output = "";
		output =hrService.addPf(hrDto.getPaidByManagement(),hrDto.getPaidByStaff(),hrDto.getDatepf(), hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deletePf")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deletePf(final HrProfileDto hrDto){
		String output = "";
		output =hrService.deletePf(hrDto.getPfids());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("saveAdvanceSalaryApproval")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveAdvanceSalaryApproval(final HrProfileDto hrDto){
		String output = "";
		output =hrService.saveAdvanceSalaryApproval(hrDto.getPaymentAdvance(),hrDto.getReason(),hrDto.getStatus(),
				hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteAdvaceSalaryApproval")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteAdvaceSalaryApproval(final HrProfileDto hrDto){
		String output = "";
		output =hrService.deleteAdvaceSalaryApproval(hrDto.getIdPayAdvanceSalary());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("salaryIssue")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response salaryIssue(final HrProfileDto hrDto){
		String output = "";
		output =hrService.salaryIssue(hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("applyLeave")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response applyLeave(final HrProfileDto hrDto){
		String output = "";
		output =hrService.applyLeave(hrDto.getCurrentAcademicYear(),hrDto.getUserAuth(),hrDto.getLeaveTypeName(),hrDto.getReason(),
				hrDto.getFromDate(),hrDto.getToDate(),hrDto.getUserName(),hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("leaveApprovals")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response leaveApprovals(final HrProfileDto hrDto){
		String output = "";
		output =hrService.leaveApprovals(hrDto.getCurrentAcademicYear(), hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("approveLeave")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response approveLeave(final HrProfileDto hrDto){
		String output = "";
		output =hrService.approveLeave(hrDto.getIdleaveapplication());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("rejectLeave")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response rejectLeave(final HrProfileDto hrDto){
		String output = "";
		output =hrService.rejectLeave(hrDto.getIdleaveapplication());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("processStaffSalary")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response processStaffSalary(final HrProfileDto hrDto){
		String output = "";
		output =hrService.processStaffSalary(hrDto.getStaffIds(),hrDto.getCurrentAcademicYear(),hrDto.getMonth(),hrDto.getYear(),
				hrDto.getBranchId(),hrDto.getDateprocess());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getPayHead")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPayHead(final HrProfileDto hrDto)throws IOException{
		String output = "";
		output =hrService.getPayHead(hrDto.getCurrentAcademicYear(),hrDto.getPayHeadType(),hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("issueStaffSalary")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response issueStaffSalary(final HrProfileDto hrDto){
		String output = "";
		output =hrService.issueStaffSalary(hrDto.getCurrentAcademicYear(),hrDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("printSalarySlip")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response printSalarySlip(final HrProfileDto hrDto){
		String output = "";
		output =hrService.printSalarySlip(hrDto.getProcessSalaryId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getStaffDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getStaffDetails(final HrProfileDto hrDto){
		String output = "";
		output =hrService.getStaffDetails(hrDto.getCurrentAcademicYear(),hrDto.getStaffId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deletePayHeadStaff")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deletePayHeadStaff(final HrProfileDto hrDto){
		String output = "";
		output =hrService.deletePayHeadStaff(hrDto.getStaffIds(),hrDto.getIdpayheadstaffdetails(),hrDto.getCurrentAcademicYear());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("issueProcessedSalary")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response issueProcessedSalary(final HrProfileDto hrDto){
		String output = "";
		output =hrService.issueProcessedSalary(hrDto.getIdProcessSalaryDetails());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("updateBasicpayEmployees")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateBasicpayEmployees(final HrProfileDto hrDto){
		String output = "";
		output =hrService.updateBasicpayEmployees(hrDto.getBranchId(),hrDto.getStaffIds(),hrDto.getBasicPay(),hrDto.getPaymentType(),
				hrDto.getAccountNo(),hrDto.getOverTime(),hrDto.getAcademicYear1());
		return Response.status(200).entity(output).build();

	}

}

