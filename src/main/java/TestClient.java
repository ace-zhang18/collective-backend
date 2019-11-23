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
			int test = 2;
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

				webTarget = client.target("http://localhost:8080/arenamaster-backend/api").path("player").path("set_device");

				invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
				
				input = "ID: 24, device_key: AAAAA";
				
				response = invocationBuilder
						.post(Entity.entity(input, MediaType.APPLICATION_JSON));

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
			case 5:
				Player player1 = new Player("penny", "petarded", "jamesgriffith@gmail.com", null , null );
				Player player2 = new Player("jacoblist", "loJ", "jlist@gmail.com", null, null);
				Player player3 = new Player("masterblake", "blm", "blakemaster@gmail.com", null, null);
				Player player4 = new Player("terabix", "anzarta", "tetra.oculus@gmail.com", null, null);
				
				try {
					//PlayerDAO.insert(player1);
					//PlayerDAO.insert(player2);
					//PlayerDAO.insert(player3);
					PlayerDAO.insert(player4);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:
				TournamentAlpha ta = new TournamentAlpha("G Series", "Cave Gaming", Calendar.getInstance().getTime(), "Counterstrike", "Single Elimination", "10621 Braddock Road – Ste B, Fairfax, VA 22032");
				TournamentAlpha ta1 = new TournamentAlpha("M Cup", "Cave Gaming", Calendar.getInstance().getTime(), "League of Legends", "Single Elimination", "10621 Braddock Road – Ste B, Fairfax, VA 22032");
				TournamentAlpha ta2 = new TournamentAlpha("B Tourney", "Cave Gaming", Calendar.getInstance().getTime(), "Overwatch", "Single Elimination" , "10621 Braddock Road – Ste B, Fairfax, VA 22032");

				
				try {
					TournamentAlphaDAO.insert(ta);
					TournamentAlphaDAO.insert(ta1);
					TournamentAlphaDAO.insert(ta2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Success!");
				break;
			case 7:
				String JSONTourney = "{\"name\":\"G Cup\",\"organization\":\"Cave Gaming\",\"date\":\"2019-07-06\",\"time\":\"04:30\",\"game\":\"Counterstrike\",\"style\":\"double-elimination\",\"location\":\"10621 Braddock Road – Ste B, Fairfax, VA 22032\"}";
				TournamentAlpha t_alpha = new TournamentAlpha(JSONTourney);

				System.out.println(t_alpha.toJSON());
			case 8: //send message from backend			
				client = JerseyClientBuilder.createClient();

				webTarget = client.target("https://fcm.googleapis.com").path("fcm").path("send");

				invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON)
						.header("Authorization", "key=AAAAX7JqQ_U:APA91bHncYYkYeoIWO8pDFzSioUFrzpQ1ZQq7iCKQFC9qAGgctpvKVzsqgvc5KyxPnnCFZRzay4i56NHasNp1zvtfm1CD2orc8q4GiHGgf9z70UtXbtfsLoB0JDYQH4vGDQh7WW20d88");

				String dKey = "cyNSp5k3mCw:APA91bH0We9Flv98Jcw8f5mWJF-3xLdICDDzK5fZ5YWe3wV48j4JFdj7LQvARWJOWoFfWcGCK6ZSvQVYYH79IcxdjkLRyp-qCbgf0zb60vSwxdyMm-p4NJG1S9qjoNDbBtPBPe5eEfp8";
				
				input = "{\r\n" + 
						"	\"notification\": {\r\n" + 
						"		\"title\": \"I DEMAND YOUR ATTENTION :)\",\r\n" + 
						"		\"text\": \"Sorry to bother you I meant, please pick an option.\",\r\n" + 
						"		\"click_action\": \"GENERAL\",\r\n" + 
						"		\"badge\": \"1\",\r\n" + 
						"		\"sound\": \"default\"\r\n" + 
						"		\r\n" + 
						"	},\r\n" + 
						"	\"content_available\": true,\r\n" + 
						"	\"data\": {\r\n" + 
						"		\"foo\": \"bar\"\r\n" + 
						"	},\r\n" + 
						"	\"priority\": \"High\",\r\n" + 
						"	\"to\": \"" + dKey + "\"\r\n" + 
						"}";


				response = invocationBuilder
						.post(Entity.entity(input, MediaType.APPLICATION_JSON));
				//.get(Response.class);

				if (response.getStatus()/100 != 2) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());
				}

				System.out.println("Output from Server .... \n");
				output = response.readEntity(String.class);
				System.out.println(output);
				break;
			case 9:
				Player p = PlayerDAO.get(24);
				System.out.println(p);
				break;
			}
			/*
 */
			/*

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

