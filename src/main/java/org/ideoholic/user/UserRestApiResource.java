package org.ideoholic.user;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ideoholic.user.dto.UserProfile;
import org.ideoholic.user.service.UserAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("user")
@Component
@Scope("singleton")
public class UserRestApiResource {
	private final static Logger logger = LoggerFactory.getLogger(UserRestApiResource.class);

	@Inject
	private UserAction userAction;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response loginUser(final UserProfile userProfile) {
		String output = "";
		logger.debug("Passed values::userProfile:" + userProfile);
		output = userAction.authenticateUser(userProfile.getUserName(), userProfile.getPassword());

		return Response.status(200).entity(output).build();
	}
}
