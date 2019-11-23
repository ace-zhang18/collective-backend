package services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.CrossOrigin;

import dao.TournamentAlphaDAO;
import objects.TournamentAlpha;


@Path("organizers")
public class OrganizerService {

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
	
}