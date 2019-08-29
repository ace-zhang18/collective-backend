package dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestDAO {
	public static String callSecondService() {
		
		Client client = ClientBuilder.newClient();
		
		WebTarget webTarget = client.target("http://localhost:8080/arenamaster-backend/api").path("test").path("func2");
		//WebTarget webTarget = client.target("https://en.wikipedia.org/wiki/List_of_HTTP_status_codes");
		
		Invocation.Builder invocationBuilder = webTarget.request();


		Response response = invocationBuilder
				.get(Response.class);

		String headers = response.getHeaders().toString();

		System.out.println(headers.toString());
		
		if (response.getStatus()/100 != 2) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response.readEntity(String.class);
		System.out.println(output);
		
		return output;
	}
	
	public static String getMessage() {
		return "Success!"; 
	}
}
