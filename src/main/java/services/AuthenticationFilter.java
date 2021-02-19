package services;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jws;
import utilities.AuthUtility;


@PreMatching
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		String token = requestContext.getHeaderString("token");
		String path = requestContext.getUriInfo().getPath();

		// No auth - check if login attempt
		/*if (token == null || token.isEmpty()) {
			if(!path.equalsIgnoreCase("users/login")) {
				requestContext.abortWith(Response.status(
						Response.Status.UNAUTHORIZED).build());
				return;
			}
		}*/
	}

}
