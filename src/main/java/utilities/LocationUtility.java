package utilities;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.catalina.util.URLEncoder;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyInvocation;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.json.JSONArray;
import org.json.JSONObject;

import objects.LatLng;

public class LocationUtility {
	public static LatLng geocode(String address) {
		
		String address_key = address.replace(' ', '+');					
		String API_key = "AIzaSyB7W-4vzJs00Pqio5An2Lwi845q_pvCpUI";			

		String request = "https://maps.googleapis.com/maps/api/geocode/json?address=" + new URLEncoder().encode(address_key, Charset.availableCharsets().get("UTF-8")) + "&key=" + API_key;
		///*
		//System.out.println(request);
		
		JerseyClient client = JerseyClientBuilder.createClient();
		
		System.out.println(request);
		System.out.println(new URLEncoder().encode(request, Charset.availableCharsets().get("UTF-8")));
		
		JerseyWebTarget webTarget = client.target(request);
		//JerseyWebTarget webTarget = client.target(new URLEncoder().encode(request, Charset.availableCharsets().get("UTF-8")));
		
		JerseyInvocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		//JerseyInvocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);
		
		Response response = invocationBuilder
				.get();
				//.get(ClientResponse.class);

		String output = response.readEntity(String.class);
		
		System.out.println(output);
		
		if (response.getStatus()/100 != 2) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		LatLng latlng = null;
		
		try {
			JSONObject geocode = new JSONObject(output);
			JSONArray results = geocode.getJSONArray("results");
			JSONObject address_components = (JSONObject) results.get(0);
			JSONObject geometry = (JSONObject) address_components.get("geometry");
			JSONObject location = (JSONObject) geometry.get("location");
			latlng = new LatLng(location.getDouble("lat"), location.getDouble("lng"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return latlng;
	}
}
