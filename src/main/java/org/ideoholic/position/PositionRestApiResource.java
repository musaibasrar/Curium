package org.ideoholic.position;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.position.dto.PositionProfileDto;
import org.ideoholic.position.service.PositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("PositionProcess")
@Component
@Scope("singleton")


public class PositionRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(PositionRestApiResource.class);
			
	@Inject
	private PositionService positionService;
	
	
	@POST
	@Path("addPosition")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addPosition(final PositionProfileDto positionDto) {
		String output = "";
		output =positionService.addPosition(positionDto.getPosition(), positionDto.getBranchId());
		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("viewPosition")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewPosition(final PositionProfileDto positionDto) {
		String output = "";
		output =positionService.viewPosition(positionDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final PositionProfileDto positionDto) {
		String output = "";
		output =positionService.deleteMultiple(positionDto.getPositionIds());
		return Response.status(200).entity(output).build();

	}
}
