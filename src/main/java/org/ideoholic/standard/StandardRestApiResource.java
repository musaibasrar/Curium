package org.ideoholic.standard;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.standard.dto.StandardProfileDto;
import org.ideoholic.standard.service.StandardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("StandardProcess")
@Component
@Scope("singleton")


public class StandardRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(StandardRestApiResource.class);
	
	@Inject
	private StandardService standardService;
	
	@POST
	@Path("viewClasses")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewClasses(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.viewClasses(standardDto.getBranchId());
		return Response.status(200).entity(output).build();

	}

	
	@POST
	@Path("createClass")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createClass(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.createClass(standardDto.getBranchId(),standardDto.getClassDetails(),standardDto.getSection());
		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("deleteClasses")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteClasses(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.deleteClasses(standardDto.getClassIds(), standardDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("addClassHierarchy")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addClassHierarchy(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.addClassHierarchy(standardDto.getBranchId(),standardDto.getLowerClass(),standardDto.getUpperClass());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteClassHierarchy")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteClassHierarchy(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.deleteClassHierarchy(standardDto.getClassIds(), standardDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("graduateMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response graduateMultiple(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.graduateMultiple(standardDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("droppedoutMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response droppedoutMultiple(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.droppedoutMultiple(standardDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("leftoutMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response leftoutMultiple(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.leftoutMultiple(standardDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("viewGraduated")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewGraduated(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.viewGraduated();
		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("viewDropped")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewDropped(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.viewDropped();
		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("viewleft")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewleft(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.viewleft();
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("restoreMultipleGraduate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response restoreMultipleGraduate(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.restoreMultipleGraduate(standardDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("restoreMultipleDroppedout")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response restoreMultipleDroppedout(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.restoreMultipleDroppedout(standardDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("restoreMultipleLeftout")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response restoreMultipleLeftout(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.restoreMultipleLeftout(standardDto.getStudentIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("searchByClass")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchByClass(final StandardProfileDto standardDto){
		String output = "";
		output =standardService.searchByClass(standardDto.getClassofStd(), standardDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
}
