package objects;

public class PlayerSearchResult {
	private String username;

	public PlayerSearchResult() {
		
	}
	
	public PlayerSearchResult(String username) {
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
