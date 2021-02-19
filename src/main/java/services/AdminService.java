package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import dao.AnnouncementDAO;
import dao.ReplyDAO;
import dao.UserDAO;
import objects_table.Announcement;
import objects_table.Reply;
import objects_table.User;
import utilities.JSONUtility;

@Path("admin")
public class AdminService {	
	

}