package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import dao.PlayerDAO;
import objects.*;

@Path("player")
public class PlayerService {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response receivePlayerLoginRequestInJSON(String input) {
		User user = PlayerDAO.login(input);
		
		return Response.status(201).entity(user.toJSON()).build();
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response receivePlayerRegistrationRequestInJSON(String request) {

		JSONObject json = new JSONObject(request);
		
		String result = "Player received: " + json.getString("username");
		System.out.println(result);
		
		Player player = new Player(json.getString("username"), json.getString("password"), json.getString("email"), null , null );
		
		try {
			PlayerDAO.insert(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "Database failure";
		}
		result = "Successfully inserted player: " + json.getString("username");
		System.out.println(result);
		
		return Response.status(201).entity(result).build();
		
	}
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getPlayerSearchResults(String request) {
		ArrayList<PlayerSearchResult> result_list = (ArrayList<PlayerSearchResult>) PlayerDAO.search(request);

		String JSONArray = "[";

		int items = 0;
		for(PlayerSearchResult item: result_list) {
			if(items > 0) JSONArray += ",";
			JSONArray += "\"";
			JSONArray += item.getUsername();
			JSONArray += "\"";
			items++;
		}
		JSONArray += "]";

		System.out.println(JSONArray);
		
		return Response.status(200).entity(JSONArray).build();
		//return "Get Success!";
		//return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("GET Success!").build();
	}
	
}