package org.ideoholic.sms;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.sms.dto.SmsProfileDto;
import org.ideoholic.sms.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("SmsProcess")
@Component
@Scope("singleton")

public class SmsRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(SmsRestApiResource.class);
	
	@Inject
	private SmsService smsService;
	
	@POST
	@Path("sendAllSMS")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response sendAllSMS(final SmsProfileDto smsDto){
		String output = "";
		output =smsService.sendAllSMS(smsDto.getBranchId(),smsDto.getAddClass(),smsDto.getAddSec(),smsDto.getMessageBody());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("sendNumbersSMS")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response sendNumbersSMS(final SmsProfileDto smsDto){
		String output = "";
		output =smsService.sendNumbersSMS(smsDto.getNumbers(),smsDto.getResultSMS());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("sendStaffSMS")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response sendStaffSMS(final SmsProfileDto smsDto){
		String output = "";
		output =smsService.sendStaffSMS(smsDto.getBranchId(),smsDto.getDepartment(),smsDto.getMessageBodyStaff());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("sendSMS")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response sendSMS(final SmsProfileDto smsDto){
		String output = "";
		output =smsService.sendSMS(smsDto.getNumbers(),smsDto.getMessage());
		return Response.status(200).entity(output).build();

	}

}
