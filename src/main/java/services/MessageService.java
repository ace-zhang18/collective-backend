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

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import dao.AnnouncementDAO;
import dao.ReplyDAO;
import dao.StatusDAO;
import dao.UserDAO;
import objects_table.*;
import utilities.AuthUtility;
import utilities.CollectiveUtility;
import utilities.JSONUtility;

@Path("messages")
public class MessageService {	

	@GET
	@Path("/announcements")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getAnnouncements() {
		ArrayList<Announcement> announcements = new ArrayList<Announcement>();
		announcements = (ArrayList)AnnouncementDAO.getInstance().getAll();
		ArrayList<JSONObject> converted = (ArrayList) CollectiveUtility.assemble(announcements);
		
		return Response.status(200).entity(JSONUtility.ToJSONArray(converted).toString()).build();
	}
	
	@GET
	@Path("/announcements/replies/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getAnnouncementReplies(@PathParam("id") String id_str) {
		long id = Long.parseLong(id_str);
		ArrayList<Reply> replies = CollectiveUtility.getPostReplies("announcements", id);
		ArrayList<JSONObject> converted = (ArrayList)CollectiveUtility.assemble(replies);
		
		return Response.status(200).entity(JSONUtility.ToJSONArray(converted).toString()).build();
	}
	
	@GET
	@Path("/{id}")
	@CrossOrigin
	public Response getReply(@PathParam("id") String id_string) {
		int id = Integer.parseInt(id_string);
		Reply reply = null;
		try {
			reply = ReplyDAO.getInstance().get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(reply.toJSONObject().toString()).build();
	}

	
	@POST
	@Path("*")
	@CrossOrigin
	public Response submitReply(JSONObject jo) {
		return Response.status(200).entity(jo.toString()).build();
	}
	
	
	@GET
	@Path("/favorite/{id}")
	@CrossOrigin
	public Response favorite(@PathParam("id") String id_string) {
		int id = Integer.parseInt(id_string);
		Reply reply = null;
		try {
			reply = ReplyDAO.getInstance().get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(reply.toJSONObject().toString()).build();
	}
	
	@GET
	@Path("/unfavorite/{id}")
	@CrossOrigin
	public Response unfavorite(@PathParam("id") String id_string) {
		int id = Integer.parseInt(id_string);
		Reply reply = null;
		try {
			reply = ReplyDAO.getInstance().get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity(reply.toJSONObject().toString()).build();
	}

}