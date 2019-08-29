package services;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;

import dao.PlayerDAO;
import dao.TestDAO;
import objects.PlayerSearchResult;


@Path("test")
public class TestService {
	@GET
	@Path("/func1")
	@Produces(MediaType.TEXT_PLAIN)
	@CrossOrigin
	public Response testFunc1() {
		System.out.println("1st service invoked");
		String response = "";
		
		response = TestDAO.callSecondService();
		
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/func2")
	@Produces(MediaType.TEXT_PLAIN)
	@CrossOrigin
	public Response testFunc2() {
		System.out.println("2nd service invoked");
		String response = "";
		
		response = TestDAO.getMessage();
		
		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/func3")
	@Produces(MediaType.TEXT_PLAIN)
	@CrossOrigin
	public String getAllHeaders(@Context HttpHeaders headers) {
		return headers.getRequestHeaders().toString();
	}
	
}
