package org.ideoholic.user;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.user.dto.UserProfileDto;
import org.ideoholic.user.service.SearchParameterDto;
import org.ideoholic.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("UserProcess")
@Component
@Scope("singleton")
public class UserRestApiResource {
	private final static Logger logger = LoggerFactory.getLogger(UserRestApiResource.class);

	@Inject
	private UserService userService;

	@POST
	@Path("authenticateUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response authenticateUser(final UserProfileDto userDto) {
		String output = "";
		logger.debug("Passed values::UserProfileDto:" + userDto);

		output = userService.authenticateUser(userDto.getUserName(), userDto.getPassword());

		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("logout")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response logout() {
		String output = "";
		output = userService.logout();
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("changePassword")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response changePassword(final UserProfileDto userDto) {
		String output = "";
		output = userService.changePassword(userDto.getCurrentPassword(), userDto.getNewPassword(),
				userDto.getConfirmNewPassword());

		return Response.status(200).entity(output).build();

	}
	
	@POST
	@Path("dashBoard")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response dashBoard(final UserProfileDto userDto) {
		String output = "";
				output = userService.dashBoard(userDto.getBranchId(), userDto.getToDate(),userDto.getFromDate());

		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("advanceSearch")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response advanceSearch(final UserProfileDto userDto) {
		String output = "";
				output = userService.advanceSearch(null, userDto.getBranchId());

		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("advanceSearchByParents")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response advanceSearchByParents(final UserProfileDto userDto) {
		String output = "";
				output = userService.advanceSearchByParents(userDto.getBranchId(),userDto.getFathersName(), userDto.getMothersName());

		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("backupData")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response backupData(final UserProfileDto userDto) {
		String output = "";
				output = userService.backupData(userDto.getFileName());

		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("searchByDate")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response searchByDate(final UserProfileDto userDto) {
		String output = "";
				output = userService.searchByDate(userDto.getBranchId(),userDto.getSelectedbranchid(),userDto.getToDate(), userDto.getFromDate(),
						userDto.getOneDay(),userDto.getDayOne(),userDto.getDateFrom(),userDto.getDateTo());

		return Response.status(200).entity(output).build();
	}

	
	

}