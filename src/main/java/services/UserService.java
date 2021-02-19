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

import dao.ReplyDAO;
import dao.StatusDAO;
import dao.UserDAO;
import objects_table.*;
import utilities.AuthUtility;
import utilities.JSONUtility;

@Path("users")
public class UserService {	

	@GET
	@Path("/profile/{id}")
	@CrossOrigin
	public Response getProfilePage(@PathParam("id") String id_string) {
		int user_id = Integer.parseInt(id_string);
		User user = null;
		try {
			user = UserDAO.getInstance().get(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(200).entity(user.toJSONObject().toString()).build();
	}

	@GET
	@Path("/status/{id}")
	@CrossOrigin
	public Response getUserStatus(@PathParam("params") String id_string) {
		int id = Integer.parseInt(id_string);
		Status status = null;
		try {
			status = StatusDAO.getInstance().get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return Response.status(200).entity(status.toJSONObject().toString()).build();
	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response loginUser(String data) {
		String response = "";
		User u = null;
		try {
			System.out.println("Login invoked");
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			JSONObject job = new JSONObject(data);
			String login = job.getString("login");			
			if(AuthUtility.is_email(login)){
				u = UserDAO.getInstance().getByEmail(login);
				if(job.has("token")) {
					if(AuthUtility.verify_token(job.getString("token"))) {
						response = AuthUtility.gen_token(u);
					}else {
						response = "0";
					}
				}else {
					if (passwordEncryptor.checkPassword(job.getString("password"), u.getPassword())) {
						response = AuthUtility.gen_token(u);
					} else {
						response = "0";
					}
				}
			}else {
				u = UserDAO.getInstance().getByUsername(login);
				if (passwordEncryptor.checkPassword(job.getString("password"), u.getPassword())) {
					response = AuthUtility.gen_token(u);
				} else {
					response = "0";
				}
			}
		}catch (NullPointerException e){
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}		

		JSONObject rstk = new JSONObject();
		rstk.put("token", response);
		if(!response.equals("0")) {
			rstk.put("username", u.getUsername());			
		}
		return Response.status(200).entity(rstk.toString()).build();
	}

	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	@CrossOrigin
	public Response registerUser(String data) {
		String response = "";
		try {
			JSONObject job = new JSONObject(data);
			User u = new User();
			u.setEmail(job.getString("email"));
			u.setUsername(job.getString("username"));
			StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(job.getString("password"));
			u.setPassword(encryptedPassword);
			UserDAO.getInstance().insert(u);
			response = "Registration successful!";
		}catch(Exception e) {
			response = "Sorry, there was an error with your request.";
			e.printStackTrace();
		}
		return Response.status(200).entity(response).build();
	}


}