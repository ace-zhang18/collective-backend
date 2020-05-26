import java.io.BufferedReader;
import java.io.FileReader;
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
import utilities.ImageUtility;
import utilities.JSONUtility;
import utilities.TemplateDAO;

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
				BufferedReader r = new BufferedReader(new FileReader("pom.xml"));
				String s = r.readLine();
				while (s != null) {
				    System.out.println(s);
				    s = r.readLine();
				}
				r.close();
				break;				
			case 1: //get All Staff Roles
				new Artwork().printClass();
				break;
			case 2:			
				client = JerseyClientBuilder.createClient();

				webTarget = client.target("http://localhost:8080/collective-backend/api").path("artworks/file").path("1");

				invocationBuilder = webTarget.request(MediaType.APPLICATION_OCTET_STREAM);

				response = invocationBuilder
						.get(Response.class);

				System.out.println(response);
				
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

