package objects_table;

import java.sql.Timestamp;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.ObjectInterface;
import utilities.JSONUtility;

public class User implements ObjectInterface{
	long id; 
	String username, email, password;
	
	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

