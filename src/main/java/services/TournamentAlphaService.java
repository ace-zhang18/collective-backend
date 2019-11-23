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

import org.springframework.web.bind.annotation.CrossOrigin;

import dao.TournamentAlphaDAO;
import objects.*;
import utilities.LocationUtility;

@Path("tournaments")
public class TournamentAlphaService {

	@POST
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	@CrossOrigin
	public String insertTournament(String data) {
		String response =  null;
		
		try {
			TournamentAlpha tourney = new TournamentAlpha(data);
		
			//System.out.println(tourney.toJSON());
			TournamentAlphaDAO.insert(tourney);
			response = "Success!";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response = e.getMessage();
		}
		
		return response;
		
	}
	//return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("GET Success!").build();
	
	
	@POST
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getTournamentSearchResults(String request) {
		ArrayList<TournamentAlpha> result_list = (ArrayList<TournamentAlpha>) TournamentAlphaDAO.search(request);
		
		String return_string = TournamentAlpha.toJSONArray(result_list);
		System.out.println(return_string);
		
		return Response.status(200).entity(return_string).build();
		//return "Get Success!";
		//return Response.status(200).header("Access-Control-Allow-Origin", "*").entity("GET Success!").build();
	}

	@GET
	@Path("/profile/{id}")
	@CrossOrigin
	public Response getTournamentByID(@PathParam("id") String id) {
		TournamentAlpha tourney = null;
		String return_string = null;
		
		if(id.matches("^[0-9]+$")) {
			try {
				tourney = TournamentAlphaDAO.get(Integer.parseInt(id));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println(id);
			//Get By Vanity URL
		}
		if(tourney != null) {
			return_string = tourney.toJSON();
		}
		return Response.status(200).entity(return_string).build();
	}

	@POST
	@Path("/location")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getLatLngFromAddress(String address) {
		//LatLng latlng = new LatLng(38.8251348, -77.3140449);
		
		LatLng latlng = LocationUtility.geocode(address);
		
		return Response.status(200).entity(latlng.toJSON()).build();		
	}
	
}