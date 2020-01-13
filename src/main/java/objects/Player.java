package objects;

import java.util.Calendar;

import utilities.JSONInterface;

public class Player implements JSONInterface{
	private int player_id;
	private String username;
	private String password;
	private String email;
	private String profile_pic;
	private String location;
	private String device_key;
	
	public Player() {
		
	}
	
	public Player(String username, String password, String email, String profile_pic, String location) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile_pic = profile_pic;
		this.location = location;
	}
	
	public Player(Long player_id, String username, String password, String email, String profile_pic, String location) {
		super();
		this.player_id = player_id.intValue();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile_pic = profile_pic;
		this.location = location;
	}
	
	public int getId() {
		return player_id;
	}
	public void setId(int id) {
		this.player_id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDeviceKey() {
		return device_key;
	}
	
	public void setDeviceKey(String device_key) {
		this.device_key = device_key;
	}
	
	public String toJSON() {
		String json = "{";
		int items = 0;
		
		if(player_id > 0) {
			json += "\"player_id\":\"" + player_id + "\"";
			items++;
		}
		if(username != null) {
			if(items > 0) json += ",";
			json += "\"username\":\"" + username + "\"";
			items++;
		}
		if(email != null) {
			if(items > 0) json += ",";
			json += "\"email\":\"" + email + "\"";
			items++;
		}
		if(profile_pic != null) {
			if(items > 0) json += ",";
			json += "\"profile_pic\":\"" + profile_pic + "\"";
			items++;
		}
		if(location != null) {
			if(items > 0) json += ",";
			json += "\"location\":\"" + location + "\"";
			items++;
		}
		json += "}";

		return json;
	}
	
	@Override
	public String toString() {
		return "{username=" + username + ", email=" + email + ", location="	+ location + "}";
	}
}
