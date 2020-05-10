import java.util.ArrayList;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.json.JSONObject;

import dao.*;
import objects.*;
import objects.Thread;
import utilities.JSONUtility;

public class TestClient {
	public static void main(String[] args) {

		try {
			JerseyClient client;
			JerseyWebTarget webTarget;
			JerseyInvocation.Builder invocationBuilder;
			Response response;
			String input, output;
			int test = 1;
			switch(test)
			{
			case 0: //get Staff Role
				Forum forum = new Forum();
				forum.setName("Main Hub");
				forum.setForum_id(0);
				ForumDAO.update(forum);
				break;				
			case 1: //get All Staff Roles
				ArrayList<Thread> list = (ArrayList<Thread>) ThreadDAO.getForumThreads(0);
				
				
				String s = JSONUtility.ToJSONArray(list);
				System.out.println(s);
				
				break;
			case 2:			
				client = JerseyClientBuilder.createClient();

				webTarget = client.target("http://localhost:8080/collective-backend/api").path("forums").path("1");

				invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

				input = "10621 Braddock Road – Ste B, Fairfax, VA 22032";


				response = invocationBuilder
						.post(Entity.entity(input, MediaType.TEXT_PLAIN));
				//.get(Response.class);

				if (response.getStatus()/100 != 2) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}

				System.out.println("Output from Server .... \n");
				output = response.readEntity(String.class);
				System.out.println(output);
				break;			
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}


}

