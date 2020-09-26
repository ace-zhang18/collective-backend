package services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import dao.ArtworkDAO;
import dao.ForumDAO;
import dao.UserDAO;
import objects.*;
import utilities.ImageUtility;
import utilities.JSONUtility;
import utilities.WebUtility;

@Path("test")
public class TestService {	
	
	@GET
	@Path("/end1")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getResponse(@PathParam("id") String id) {
		return Response.status(200).entity("It works!").build();
	}
}