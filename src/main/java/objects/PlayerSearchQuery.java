package objects;

public class PlayerSearchQuery {
	private String username;

	public PlayerSearchQuery() {
		
	}
	
	public PlayerSearchQuery(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	
}
