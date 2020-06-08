package org.ideoholic.admin;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.admin.dto.AdminProfileDto;
import org.ideoholic.admin.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("AdminProcess")
@Component
@Scope("singleton")

public class AdminRestApiResource {
	
	private final static Logger logger = LoggerFactory.getLogger(AdminRestApiResource.class);
	
	@Inject
	private AdminService adminService;
	
	@POST
	@Path("viewAllExpenses")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewAllExpenses(final AdminProfileDto adminDto){
		String output = "";
		output =adminService.viewAllExpenses(adminDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	
	@POST
	@Path("addExpenses")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addExpenses(final AdminProfileDto adminDto){
		String output = "";
		output =adminService.addExpenses(adminDto.getBranchId(),adminDto.getItem(),adminDto.getQuantity(),adminDto.getPrice(),
				adminDto.getEntrydate());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final AdminProfileDto adminDto){
		String output = "";
		output =adminService.deleteMultiple(adminDto.getExpensesIds());
		return Response.status(200).entity(output).build();

	}

}
