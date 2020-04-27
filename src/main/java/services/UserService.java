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

import dao.UserDAO;
import objects.*;
import utilities.JSONUtility;

@Path("user")
public class UserService {	
	
	@POST
	@Path("/update/profile")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateProfilePage(String data) {
		JSONObject json = new JSONObject(data);

		int user_id = json.getInt("id");
		
		try {
			User user = UserDAO.get(user_id);
			String new_text = json.getString("data");
			user.setProfile_page(new_text);
			UserDAO.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String return_string = "Page successfully updated!";
		
		return Response.status(200).entity(return_string).build();
		//return "Get Success!";
		//return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("GET Success!").build();
	}
	
	@GET
	@Path("/profile/{id}")
	@CrossOrigin
	public Response getProfilePage(@PathParam("id") String id) {
		int user_id = Integer.parseInt(id);
		User user;
		String return_string = "";
		try {
			user = UserDAO.get(user_id);
			return_string = user.getProfile_page();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return_string = "error loading profile page";
			e.printStackTrace();
		}
		
		return Response.status(200).entity(return_string).build();
	}
	
}