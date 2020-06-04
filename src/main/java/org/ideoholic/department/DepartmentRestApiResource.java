package org.ideoholic.department;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.department.dto.DepartmentProfileDto;
import org.ideoholic.department.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("FeesProcess")
@Component
@Scope("singleton")

public class DepartmentRestApiResource {

	private final static Logger logger = LoggerFactory.getLogger(DepartmentRestApiResource.class);
	
	@Inject
	private DepartmentService departmentService; 
	
	@POST
	@Path("addDepartment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addDepartment(final DepartmentProfileDto departDto) {
		String output = "";
		output =departmentService.addDepartment(departDto.getBranchId(), departDto.getDepartment1());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewDepartment")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewDepartment(final DepartmentProfileDto departDto) {
		String output = "";
		output =departmentService.viewDepartment(departDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final DepartmentProfileDto departDto) {
		String output = "";
		output =departmentService.deleteMultiple(departDto.getDepartmentIds());
		return Response.status(200).entity(output).build();

	}
}
