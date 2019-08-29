package objects;

public class User {
	int id;
	private String username, token;

	public User() {
		super();
	}
	
	public User(int id, String username, String token) {
		super();
		this.id = id;
		this.username = username;
		this.token = token;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String toJSON() {
		String json = "{";
		int items = 0;
		
		if(id > 0) {
			if(items > 0) json += ",";
			json += "\"id\":\"" + id + "\"";
			items++;
		}
		
		if(username != null) {
			if(items > 0) json += ",";
			json += "\"username\":\"" + username + "\"";
			items++;
		}
		
		if(token != null) {
			if(items > 0) json += ",";
			json += "\"token\":\"" + token + "\"";
			items++;
		}
		json += "}";
		
		return json;
	}
	
	
}
