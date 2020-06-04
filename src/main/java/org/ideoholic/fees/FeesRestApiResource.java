package org.ideoholic.fees;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.fees.dto.FeesProfileDto;
import org.ideoholic.fees.service.FeesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("FeesProcess")
@Component
@Scope("singleton")


public class FeesRestApiResource {
	private final static Logger logger = LoggerFactory.getLogger(FeesRestApiResource.class);
	
	@Inject
	private FeesService feesService;

	
	@POST
	@Path("viewFees")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewFees(final FeesProfileDto feesDto) {
		String output = "";
		output = feesService.viewFees(feesDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addFeesParticular")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addFeesParticular(final FeesProfileDto feesDto) {
		String output = "";
		output = feesService.addFeesParticular(feesDto.getBranchId(), feesDto.getFeescategory1(), feesDto.getFromclass(), 
				feesDto.getToclass(), feesDto.getAmount());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final FeesProfileDto feesDto) {
		String output = "";
		output = feesService.deleteMultiple(feesDto.getIdfeescategory());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteFeesCategory")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteFeesCategory(final FeesProfileDto feesDto) {
		String output = "";
		output = feesService.deleteFeesCategory(feesDto.getIdfeescategory(), feesDto.getStudentId());
		return Response.status(200).entity(output).build();

	}
}
