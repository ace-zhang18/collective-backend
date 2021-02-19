package services;

import java.util.ArrayList;
import java.util.HashSet;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import objects_table.Reaction;
import utilities.CollectiveUtility;
import utilities.JSONUtility;

@Path("reactions")
public class ReactionService {	

	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getReactions(@PathParam("id") String id_string) {
		long uid = Long.parseLong(id_string);
		
		ArrayList<JSONObject> reacts = new ArrayList<JSONObject>();
		HashSet<Reaction> reacts_hash = new HashSet<Reaction>();
		ArrayList<Reaction> avail_reacts = CollectiveUtility.getAvailableReactions(uid);
		
		for(Reaction r: avail_reacts) {
			JSONObject jo = r.toJSONObject();
			jo.put("locked", false);
			reacts.add(jo);
		}
		
		ArrayList<Reaction> locked_reacts = CollectiveUtility.getLockedReactions(uid);
		
		for(Reaction r: locked_reacts) {
			JSONObject jo = r.toJSONObject();
			jo.put("locked", false);
			reacts.add(jo);
		}
		
		return Response.status(200).entity(JSONUtility.ToJSONArray(reacts).toString()).build();
	}

}