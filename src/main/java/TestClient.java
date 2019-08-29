import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Iterator;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;

import java.util.ArrayList;
import java.util.Calendar;

import dao.PlayerDAO;
import dao.SqlSessionContainer;
import dao.TournamentAlphaDAO;
import objects.LatLng;
import objects.Player;
import objects.TournamentAlpha;
import utilities.LocationUtility;

public class TestClient {
	public static void main(String[] args) {

		try {
			JerseyClient client;
			JerseyWebTarget webTarget;
			JerseyInvocation.Builder invocationBuilder;
			Response response;
			String input, output;
			int test = 4;
			switch(test)
			{
			case 0:
				String address = "10621 Braddock Road – Ste B, Fairfax, VA 22032";

				LatLng latlng= LocationUtility.geocode(address); 

				System.out.println(latlng);
				break;
			case 1:			
				client = JerseyClientBuilder.createClient();

				webTarget = client.target("http://localhost:8080/arenamaster-backend/api").path("tournaments").path("location");

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
			case 2:
				///*
				//call within a call sample test
				client = JerseyClientBuilder.createClient();

				webTarget = client.target("http://localhost:8080/arenamaster-backend/api").path("test").path("func1");

				invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);

				response = invocationBuilder
						.get(Response.class);

				if (response.getStatus()/100 != 2) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}

				System.out.println("Output from Server .... \n");
				output = response.readEntity(String.class);
				System.out.println(output);
				break;
			case 3:
				String charsets = Charset.availableCharsets().toString();
				
				System.out.println(charsets);
				
				break;
			case 4:
				String login = "{\"username\":\"terabix\",\"password\":\"bradley01\"}";
				
				System.out.println(login);
				break;
			}
			//System.out.println(Calendar.getInstance().getTime());

			/*Player player1 = new Player("pennywise", "petarded", "jamesgriffith@gmail.com", null , null );
			Player player2 = new Player("jacoblist", "petarded", "jlist@gmail.com", "", "");
			Player player3 = new Player("masterblake", "petarded", "blakemaster@gmail.com", "", "");

			try {
				PlayerDAO.insert(player1);
				PlayerDAO.insert(player2);
				PlayerDAO.insert(player3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			/*
			TournamentAlpha ta = new TournamentAlpha("Test Tourney", "Cave Gaming", Calendar.getInstance().getTime(), "Teamfight Tactics", "Herndon, Virginia", "Double Elimination");

			try {
				TournamentAlphaDAO.insert(ta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Success!");
			 */
			/*
			String JSONTourney = "{\"name\":\"G Cup\",\"organization\":\"Cave Gaming\",\"date\":\"2019-07-06\",\"time\":\"04:30\",\"game\":\"Counterstrike\",\"style\":\"double-elimination\",\"location\":\"10621 Braddock Road – Ste B, Fairfax, VA 22032\"}";
			TournamentAlpha t_alpha = new TournamentAlpha(JSONTourney);

			System.out.println(t_alpha.toJSON());
			 */
			/*
			ArrayList<TournamentAlpha> tourney_list = (ArrayList<TournamentAlpha>) TournamentAlphaDAO.getAll();

			for(TournamentAlpha tourney: tourney_list){
				System.out.println(tourney.toJSON());
			}
			 */

			/*
			Iterator iter = location.keys();

			while(iter.hasNext()) {
				System.out.println(iter.next());
			}*/

		} catch (Exception e) {

			e.printStackTrace();

		}

	}


}

