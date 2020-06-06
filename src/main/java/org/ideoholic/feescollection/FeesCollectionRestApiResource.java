package org.ideoholic.feescollection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.feescollection.dto.FeesCollectionProfileDto;
import org.ideoholic.feescollection.service.FeesCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("FeesCollectionProcess")
@Component
@Scope("singleton")

public class FeesCollectionRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(FeesCollectionRestApiResource.class);
	
	@Inject
	private FeesCollectionService feesCollectionService;
	
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response add(final FeesCollectionProfileDto FeesDto) {
		String output = "";
		output =feesCollectionService.add(null,FeesDto.getSid(), FeesDto.getFeesIDS(),FeesDto.getFeesMonths(),FeesDto.getFeesAmounts(),
				FeesDto.getFeesCat());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("getStampFees")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getStampFees(final FeesCollectionProfileDto FeesDto) {
		String output = "";
		output =feesCollectionService.getStampFees(FeesDto.getCurrentAcademicYear(), FeesDto.getId(),FeesDto.getStudentName(),FeesDto.getAdmno(),
				FeesDto.getClassandsec(),FeesDto.getStudentId(),FeesDto.getDateoffees());
		return Response.status(200).entity(output).build();

	}
	
	
	
	@POST
	@Path("cancelFeesReceipt")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response cancelFeesReceipt(final FeesCollectionProfileDto FeesDto) {
		String output = "";
		output =feesCollectionService.cancelFeesReceipt(FeesDto.getCurrentAcademicYear(),FeesDto.getReceiptId());
		return Response.status(200).entity(output).build();

	}
	
	
	@POST
	@Path("viewCancelledReceipts")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewCancelledReceipts(final FeesCollectionProfileDto FeesDto) {
		String output = "";
		output =feesCollectionService.viewCancelledReceipts(FeesDto.getBranchId(),FeesDto.getBranchId1(),FeesDto.getToDate(),
				FeesDto.getFromDate(),FeesDto.getOneDay(),FeesDto.getDayOne(),FeesDto.getDayonecancel(),FeesDto.getDatefromcancel(),
				FeesDto.getDatetocancel());
		return Response.status(200).entity(output).build();

	}
	

}
