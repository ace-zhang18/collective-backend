package services;

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

import dao.ForumDAO;
import dao.ThreadDAO;
import dao.UserDAO;
import objects.*;
import objects.Thread;
import utilities.JSONUtility;

@Path("forums")
public class ForumService {	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getForum(@PathParam("id") String id) {
		int forum_id = Integer.parseInt(id);
		Forum f = null;
		try {
			f = ForumDAO.getInstance().get(forum_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(f.toJSON().toString()).build();
	}
	
	@GET
	@Path("/sub/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getSubforums(@PathParam("id") String id) {
		int forum_id = Integer.parseInt(id);
		ArrayList<Forum> list = null;
		try {
			list = (ArrayList<Forum>) ForumDAO.getSub(forum_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String return_string = JSONUtility.ToJSONArray(list);

		return Response.status(200).entity(return_string).build();
	}
	
	@GET
	@Path("/threads/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getForumThreads(@PathParam("id") String id) {
		int forum_id = Integer.parseInt(id);
		ArrayList<Thread> list = null;
		try {
			list = (ArrayList<Thread>) ThreadDAO.getForumThreads(forum_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String return_string = JSONUtility.ToJSONArray(list);

		return Response.status(200).entity(return_string).build();
	}
	
}