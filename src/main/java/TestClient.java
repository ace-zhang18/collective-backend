import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;

import dao.ArtTagDAO;
import objects.ArtTag;

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
				ArtTag sci_fi = new ArtTag();
				sci_fi.setName("Sci-fi");
				sci_fi.setParent(104);
				ArtTagDAO.getInstance().insertNew(sci_fi);
				ArtTag concept_art = new ArtTag();
				concept_art.setName("Concept Art");
				concept_art.setParent(104);
				ArtTagDAO.getInstance().insertNew(concept_art);
				ArtTag fantasy = new ArtTag();
				fantasy.setName("Fantasy");
				fantasy.setParent(104);
				ArtTagDAO.getInstance().insertNew(fantasy);
				ArtTag horror = new ArtTag();
				horror.setName("Horror");
				horror.setParent(104);
				ArtTagDAO.getInstance().insertNew(horror);
				ArtTag anime = new ArtTag();
				anime.setName("Anime");
				anime.setParent(104);
				ArtTagDAO.getInstance().insertNew(anime);
				ArtTag fashion = new ArtTag();
				fashion.setName("Fashion");
				fashion.setParent(104);
				ArtTagDAO.getInstance().insertNew(fashion);
				ArtTag architecture = new ArtTag();
				architecture.setName("Architecture");
				architecture.setParent(104);
				ArtTagDAO.getInstance().insertNew(architecture);
				ArtTag character_design = new ArtTag();
				character_design.setName("Character Design");
				character_design.setParent(104);
				ArtTagDAO.getInstance().insertNew(character_design);
				ArtTag fan_art = new ArtTag();
				fan_art.setName("Fan Art");
				fan_art.setParent(104);
				ArtTagDAO.getInstance().insertNew(fan_art);
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

