package org.ideoholic.employee;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.employee.dto.EmployeeProfileDto;
import org.ideoholic.employee.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("EmployeeProcess")
@Component
@Scope("singleton")


public class EmployeeRestApiResource {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeRestApiResource.class);
			
			@Inject
			private EmployeeService employeeService;
	
	@POST
	@Path("addEmployee")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response addEmployee(final EmployeeProfileDto employeeDto) {
		String output = "";
		output =employeeService.addEmployee(employeeDto.getBranchId(),null,null);
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("ViewAllEmployee")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response ViewAllEmployee(final EmployeeProfileDto employeeDto) {
		String output = "";
		output =employeeService.ViewAllEmployee(employeeDto.getBranchId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("viewDetailsEmployee")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response viewDetailsEmployee(final EmployeeProfileDto employeeDto) {
		String output = "";
		output =employeeService.viewDetailsEmployee(employeeDto.getId());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("updateEmployee")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response updateEmployee(final EmployeeProfileDto employeeDto) {
		String output = "";
		output =employeeService.updateEmployee(employeeDto.getId1(), employeeDto.getLeavingdate(),null);
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("deleteMultiple")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteMultiple(final EmployeeProfileDto employeeDto) {
		String output = "";
		output =employeeService.deleteMultiple(employeeDto.getEmployeeIds());
		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("searchEmployee")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchEmployee(final EmployeeProfileDto employeeDto) {
		String output = "";
		output =employeeService.searchEmployee(employeeDto.getStaffName(), employeeDto.getStaffDepartment(), employeeDto.getBranchId(),null);
		return Response.status(200).entity(output).build();

	}
}
