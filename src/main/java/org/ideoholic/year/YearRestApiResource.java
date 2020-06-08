package org.ideoholic.year;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.year.dto.YearProfileDto;
import org.ideoholic.year.service.YearService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("YearProcess")
@Component
@Scope("singleton")

public class YearRestApiResource {

	private final static Logger logger = LoggerFactory.getLogger(YearRestApiResource.class);
	
	@Inject
	private YearService yearService;
	
	@POST
	@Path("saveYear")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response saveYear(final YearProfileDto yearDto){
		String output = "";
		output =yearService.saveYear(yearDto.getAcademicYear());
		return Response.status(200).entity(output).build();

	}
}
