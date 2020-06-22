package org.ideoholic.period;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.period.dto.PeriodProfileDto;
import org.ideoholic.period.service.PeriodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("PeriodProcess")
@Component
@Scope("singleton")

public class PeriodRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(PeriodRestApiResource.class);
	
	@Inject
	private PeriodService periodService;
	
	@POST
	@Path("periodConfiguration")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response periodConfiguration(final PeriodProfileDto periodDto){
		String output = "";
		output =periodService.periodConfiguration(periodDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("savePeriods")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response savePeriods(final PeriodProfileDto periodDto){
		String output = "";
		output =periodService.savePeriods(periodDto.getBranchId(),null);
		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("viewTimeTable")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewTimeTable(final PeriodProfileDto periodDto){
		String output = "";
		output =periodService.viewTimeTable(periodDto.getPeriodMasterid());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deletePeriods")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deletePeriods(final PeriodProfileDto periodDto){
		String output = "";
		output =periodService.deletePeriods(periodDto.getPeriodMasterid1());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("generateTimeTable")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response generateTimeTable(final PeriodProfileDto periodDto){
		String output = "";
		output =periodService.generateTimeTable(periodDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewTeacherTimeTable")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewTeacherTimeTable(final PeriodProfileDto periodDto){
		String output = "";
		output =periodService.viewTeacherTimeTable(periodDto.getBranchId(),periodDto.getTeacherName());
		return Response.status(200).entity(output).build();

	}
}
