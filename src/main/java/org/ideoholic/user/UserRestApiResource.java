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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response logout() {
		String output = "";
		output = userService.logout();
		return Response.status(200).entity(output).build();
	}

}
