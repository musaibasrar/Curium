package org.ideoholic.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Path("user")
@Component
@Scope("singleton")
public class UserRestApiResource {
	private final static Logger logger = LoggerFactory.getLogger(UserRestApiResource.class);

	@GET
	//@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response loginUser() {
		logger.debug("putting log here");
		String output = "Jersey say : " + "Hello World!";

        return Response.status(200).entity(output).build();
	}
}
