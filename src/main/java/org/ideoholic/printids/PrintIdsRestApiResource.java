package org.ideoholic.printids;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.printids.dto.PrintIdsProfileDto;
import org.ideoholic.printids.service.PrintIdsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("PrintIdsProcess")
@Component
@Scope("singleton")

public class PrintIdsRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(PrintIdsRestApiResource.class);
	
	@Inject
	private PrintIdsService printIdsService;
	
	@POST
	@Path("searchDetails")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchDetails(final PrintIdsProfileDto printDto){
		String output = "";
		output =printIdsService.searchDetails(printDto.getBranchId(),printDto.getStudentName(),printDto.getAddClass(),
				printDto.getAddSec());
		return Response.status(200).entity(output).build();

	}

}
