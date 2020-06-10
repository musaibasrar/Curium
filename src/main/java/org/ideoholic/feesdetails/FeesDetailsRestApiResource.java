package org.ideoholic.feesdetails;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.feesdetails.dto.FeesDetailsProfileDto;
import org.ideoholic.feesdetails.service.FeesDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("FeesDetailsProcess")
@Component
@Scope("singleton")

public class FeesDetailsRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(FeesDetailsRestApiResource.class);
	
	@Inject
	private FeesDetailsService feesDetailsService;
	
	@POST
	@Path("exportDataForFees")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response exportDataForFees(final FeesDetailsProfileDto feesDto){
		String output = "";
		output =feesDetailsService.exportDataForFees(feesDto.getFeesIds());
		return Response.status(200).entity(output).build();

	}
	

}
