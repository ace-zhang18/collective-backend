package dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import objects.Player;
import objects.PlayerSearchResult;
import objects.User;

public class GuestPlayerDAO {
	public static void insert(Player player) throws Exception{
		SqlSessionContainer.getSession().insert("Player.insert", player);
		System.out.println("Player successfully inserted");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static void update(Player player) throws Exception{
		SqlSessionContainer.getSession().update("Player.update", player);
		System.out.println("Player successfully updated");
	    SqlSessionContainer.getSession().commit();
	    SqlSessionContainer.getSession().close();
	}
	
	public static Player get(int player_id) throws Exception{
		Player p;
		
		p = SqlSessionContainer.getSession().selectOne("Player.get", player_id);
		
		return p;		
	}
	
	public static List<Player> getAll() throws Exception{
		List<Player> player_list = new ArrayList<>();
		
		player_list = SqlSessionContainer.getSession().selectList("Player.getAll");
		
		return player_list;		
	}
	
	public static List<PlayerSearchResult> search(String request){
		ArrayList<Player> player_list = null;
		try {
			player_list = (ArrayList<Player>)getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PlayerSearchResult> result_list = new ArrayList<PlayerSearchResult>();
		
		for(Player player: player_list) {
			if(player.getUsername().contains(request)) {
				result_list.add(new PlayerSearchResult(player.getUsername()));
			}
		}
		
		return result_list;
	}
	
	public static User login(String login) {
		JSONObject json = new JSONObject(login);
		
		Player p = SqlSessionContainer.getSession().selectOne("Player.getByUsername", json.getString("username"));
		
		User user = new User();
		
		user.setId(p.getId());
		user.setUsername(p.getUsername());
		
		if(json.getString("password").equals(p.getPassword())) {
			user.setToken("fake-jwt-token");
		}
		
		return user;
	}
	
	public static void setDeviceKey(int player_id, String key) {
		Player p = SqlSessionContainer.getSession().selectOne("Player.get", player_id);
		p.setDeviceKey(key);
		try {
			update(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
