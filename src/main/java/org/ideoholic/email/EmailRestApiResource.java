package org.ideoholic.email;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.email.dto.EmailProfileDto;
import org.ideoholic.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("EmailProcess")
@Component
@Scope("singleton")

public class EmailRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(EmailRestApiResource.class);
	
	@Inject
	private EmailService emailService;
	
	@POST
	@Path("sendAllEmail")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response sendAllEmail(final EmailProfileDto emailDto){
		String output = "";
		output =emailService.sendAllEmail(emailDto.getBranchId(),emailDto.getAddClass(),emailDto.getAddSec(),
				emailDto.getSubject(),emailDto.getMessageBody());
		return Response.status(200).entity(output).build();

	}

}
