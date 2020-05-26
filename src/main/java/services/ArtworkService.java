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
import utilities.JSONUtility;
import utilities.WebUtility;

@Path("artworks")
public class ArtworkService {	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getArtwork(@PathParam("id") String id) {
		int art_id = Integer.parseInt(id);
		Artwork a = null;
		try {
			a = ArtworkDAO.get(art_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(a.toJSON().toString()).build();
	}
	
	@GET
	@Path("/file/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	@CrossOrigin
	public Response getArtworkFile(@PathParam("id") String id) {
		int art_id = Integer.parseInt(id);
		Artwork a = null;
		try {
			a = ArtworkDAO.get(art_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String path = "C:\\collective-backend\\assets\\" + art_id + "\\"+ art_id + "." + a.getFile_type();
		
		try {
			return Response.status(200).entity(WebUtility.getFileStream(path)).build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.status(500).entity("Failure").build();
		}
	}
}