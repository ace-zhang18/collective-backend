import java.awt.List;
import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.ibatis.session.SqlSession;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.json.JSONArray;
import org.json.JSONObject;
import org.postgresql.util.PGobject;

import dao.ArtTagDAO;
import dao.ArtworkDAO;
import dao.GalleryDAO;
import dao.SqlSessionContainer;
import dao.UserDAO;
import interfaces.Mapper;
import interfaces.ObjectInterface;
import objects.ArtTag;
import objects.Artwork;
import objects.Gallery;
import objects.User;
import utilities.ArrayTree;
import utilities.JSONUtility;
import utilities.WebUtility;

public class TestClient {
	public static void main(String[] args) {

		try {
			JerseyClient client;
			JerseyWebTarget webTarget;
			JerseyInvocation.Builder invocationBuilder;
			Response response;
			String input, output;
			int test = 10;
			switch(test)
			{
			case 1:
				User user = new User();
				user.setUserame("terabix");
				System.out.println(UserDAO.getInstance().insertNew(user));
				break;
			case 2:
				Gallery g = GalleryDAO.getInstance().get(100);
				System.out.print(g);
				break;
			case 3:
				break;
			case 4:
				String query ="102 106";
				WebUtility.processSearchQuery(query);
				break;
			case 5:
				
				break;
			case 6:
				JSONObject j1 = new JSONObject("{\"users\":[10]}");
				JSONObject j2 = new JSONObject("{\"users\":[100]}");
				
				break;
			case 7:
				Gallery g1 = GalleryDAO.getInstance().get(100);
				long[] col = g1.getCollection();
				
				ArrayList<Artwork> art_list = (ArrayList)ArtworkDAO.getInstance().getSet(col);
				for(Artwork a: art_list) {
					System.out.print(a.getId() + " ");
				}
				
				
				break;
			case 8:
				ArrayList<ArtTag> ar = (ArrayList) ArtTagDAO.getInstance().getByNameMulti("field star");
				for(ArtTag a: ar) {
					System.out.println(a.getId());
				}
				break;
			case 9:
				System.out.println(ArtworkDAO.getInstance().constructQuery(WebUtility.processSearchQuery("air {-horse~train} sea -foot {anchor~{-field gray}}")));
				break;
			case 10:
				client = JerseyClientBuilder.createClient();

				webTarget = client.target("http://localhost:8080/collective-backend/api/artworks/latest");
				
				invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

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

	public static void PrintTree(ArrayTree<String> tree) {
		System.out.println(tree.getData());
		for(ArrayTree node: tree.getNodes()) {
			PrintTree(node);
		}
	}

}

