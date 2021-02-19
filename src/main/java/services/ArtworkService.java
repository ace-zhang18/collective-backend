package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import dao.ArtworkDAO;
import dao.ArtworkUserDAO;
import dao.UserDAO;
import objects_table.Artwork;
import objects_table.ArtworkUser;
import objects_table.User;
import utilities.CollectiveUtility;
import utilities.ImageUtility;
import utilities.JSONUtility;
import utilities.WebUtility;

@Path("artworks")
public class ArtworkService {	
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getArtwork(@PathParam("id") String id) {
		String[] toks = id.split(",");
		
		JSONObject jo = new JSONObject();
		long[] ids = new long[toks.length];
		for(int i = 0; i < toks.length; i++) {
			ids[i] = Long.parseLong(toks[i]);
		}
		
		List<Artwork> art_list = (ArrayList)ArtworkDAO.getInstance().getSet(ids);
		
		List<JSONObject> obj_list = (ArrayList)CollectiveUtility.assemble(art_list);
		
		return Response.status(200).entity(obj_list.toString()).build();
	}
	
	@GET
	@Path("/latest")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getLatestArt() {
		int count = 5;
		
		ArrayList<Artwork> latest = (ArrayList)ArtworkDAO.getInstance().getLatest(count);
		
		JSONObject jo = new JSONObject();
		
		JSONArray urls = new JSONArray();
		
		for(Artwork a: latest) {
			urls.put(ImageUtility.getImageUrl(a));
		}
		
		jo.put("urls", urls);

		return Response.status(200).entity(jo.toString()).build();
	}
}