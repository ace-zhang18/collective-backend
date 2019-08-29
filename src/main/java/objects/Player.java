package objects;

public class Player {
	private int player_id;
	private String username;
	private String password;
	private String email;
	private String profile_pic;
	private String location;
	
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
	
	@Override
	public String toString() {
		return "{username=" + username + ", email=" + email + ", profile_pic=" + profile_pic + ", location="
				+ location + "}";
	}
}
