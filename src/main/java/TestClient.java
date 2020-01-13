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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import dao.GuestPlayerDAO;
import dao.PlayerDAO;
import dao.SqlSessionContainer;
import dao.TournamentAlphaDAO;
import objects.GuestPlayer;
import objects.LatLng;
import objects.Player;
import objects.TournamentAlpha;
import utilities.JSONUtility;
import utilities.LocationUtility;
import utilities.TournamentUtility;

public class TestClient {
	public static void main(String[] args) {

		try {
			JerseyClient client;
			JerseyWebTarget webTarget;
			JerseyInvocation.Builder invocationBuilder;
			Response response;
			String input, output;
			int test = 11;
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
				ArrayList<Player> players = new ArrayList<Player>();
				/*players.add(new Player("alex_jones", "sameness", "alex_jones@gmail.com", null , null )); //1
				players.add(new Player("mike_smith", "slimshady", "mike_smith@gmail.com", null , null ));  //2
				players.add(new Player("tyler_banes", "circulation", "tyler_banes@gmail.com", null , null ));  //3
				players.add(new Player("alex_bradley", "platinum", "alex_bradley@gmail.com", null , null ));  //4
				players.add(new Player("zach_foster", "disco", "zach_foster@gmail.com", null , null ));  //5
				players.add(new Player("alex_banes", "shark", "alex_banes@gmail.com", null , null ));  //6
				players.add(new Player("mason_jones", "teeth", "mason_jones@gmail.com", null , null ));  //7
				players.add(new Player("alex_smith", "firebase", "alex_smith@gmail.com", null , null ));  //8
				players.add(new Player("tyler_foster", "kilgore", "tyler_foster@gmail.com", null , null ));  //9
				players.add(new Player("alex_kilgore", "icecube", "alex_kilgore@gmail.com", null , null ));  //10
				players.add(new Player("james_bradley", "flounder", "james_bradley@gmail.com", null , null ));  //11
				players.add(new Player("issac_smith", "rocket", "issac_smith@gmail.com", null , null ));  //12
				players.add(new Player("mike_lennox", "soccerball", "mike_lennox@gmail.com", null , null ));  //13
				players.add(new Player("tyler_frack", "tsunami", "tyler_frack@gmail.com", null , null ));  //14
				players.add(new Player("zach_markel", "gambino", "zach_markel@gmail.com", null , null ));  //15
				players.add(new Player("james_banes", "redline", "james_banes@gmail.com", null , null ));   //16
				players.add(new Player("jeffrey_winkler", "mastery", "jeffrey_winkler@gmail.com", null , null ));   //17
				players.add(new Player("simon_marshall", "orange", "simon_marshall@gmail.com", null , null ));   //18*/
				
				try {
					for(Player p: players) {
						PlayerDAO.insert(p);
					}
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
				ArrayList<Player> p = (ArrayList<Player>) PlayerDAO.getAll();
				ArrayList<TournamentAlpha> t = (ArrayList<TournamentAlpha>) TournamentAlphaDAO.getAll();
				
				System.out.println(JSONUtility.ToJSONArray(p));
				System.out.println(JSONUtility.ToJSONArray(t));
				
				break;
			case 10:
				GuestPlayer g1 = new GuestPlayer(21, 8);
				GuestPlayer g2 = new GuestPlayer(22, 8);
				GuestPlayerDAO.insert(g1);
				GuestPlayerDAO.insert(g2);
				break;
			case 11:
				ArrayList<Player> c = (ArrayList<Player>) PlayerDAO.getAll();
				
				//Collections.shuffle(c);

				TournamentUtility.generateSeedings(c);
				
				break;
			}
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

