package org.ideoholic.stampfees;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.stampfees.dto.StampFeesProfileDto;
import org.ideoholic.stampfees.service.StampFeesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("StampFeesProcess")
@Component
@Scope("singleton")

public class StampFeesRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(StampFeesRestApiResource.class);
	
	@Inject
	private StampFeesService stampFeesService;
	
	@POST
	@Path("advanceSearch")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response advanceSearch(final StampFeesProfileDto stampDto){
		String output = "";
		output =stampFeesService.advanceSearch(stampDto.getBranchId(), stampDto.getStudentname(), stampDto.getAddClass(),
				stampDto.getAddSec());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteFeesStamp")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteFeesStamp(final StampFeesProfileDto stampDto){
		String output = "";
		output =stampFeesService.deleteFeesStamp(stampDto.getCurrentYear(), stampDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}

}
