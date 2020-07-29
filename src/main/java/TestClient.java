import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;

public class TestClient {
	public static void main(String[] args) {

		try {
			JerseyClient client;
			JerseyWebTarget webTarget;
			JerseyInvocation.Builder invocationBuilder;
			Response response;
			String input, output;
			int test = 0;
			switch(test)
			{
			case 0:				
								
				break;
			case 10:			
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

