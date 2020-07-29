package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.CrossOrigin;

import dao.PostDAO;
import dao.ThreadDAO;
import objects.Post;
import objects.Thread;
import utilities.JSONUtility;

@Path("threads")
public class ThreadService {	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getThread(@PathParam("id") String id) {
		int thread_id = Integer.parseInt(id);
		Thread t = null;
		try {
			t = ThreadDAO.getInstance().get(thread_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(t.toJSON().toString()).build();
	}
	
	
	@GET
	@Path("/posts/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getPosts(@PathParam("id") String id) {
		int thread_id = Integer.parseInt(id);
		ArrayList<Post> list = null;
		try {
			list = (ArrayList<Post>) PostDAO.getInstance().getThreadPosts(thread_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String return_string = JSONUtility.ToJSONArray(list);

		return Response.status(200).entity(return_string).build();
	}
}