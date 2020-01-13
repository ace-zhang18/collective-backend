package objects;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import utilities.JSONInterface;



public class TournamentAlpha implements JSONInterface{
	int tournament_id;
	String name;
	String organization;
	Date date;
	String game;
	String style;
	String location;
	int locked;
	
	public TournamentAlpha() {
		super();
	}

	public TournamentAlpha(String name, String organization, Date date, String game, String style, String location) {
		super();
		this.name = name;
		this.organization = organization;
		this.date = date;
		this.game = game;
		this.style = style;
		this.location = location;
		this.locked = 0;
	}

	public TournamentAlpha(int tournament_id, String name, String organization, Date date, String game, String style,
			String location) {
		super();
		this.tournament_id = tournament_id;
		this.name = name;
		this.organization = organization;
		this.date = date;
		this.game = game;
		this.style = style;
		this.location = location;
		this.locked = 0;
	}

	public TournamentAlpha(String s) {
		try {
			JSONObject jo = new JSONObject(s);
			Iterator<String> iter = jo.keys();
			Calendar calendar = Calendar.getInstance();
			int year = 0, month = 0, days = 0, hour = 0, minute = 0;

			while(iter.hasNext()) {
				String key = iter.next();
				if(key.equals("name")) {
					name = jo.getString(key);
				}else if(key.equals("organization")) {
					organization = jo.getString(key);
				}else if(key.equals("date")) {
					String[] tok = jo.getString(key).split("-");
					try {
						year = Integer.parseInt(tok[0]);
						month = Integer.parseInt(tok[1]);
						days = Integer.parseInt(tok[2]);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else if(key.equals("time")) {
					String[] tok = jo.getString(key).split(":");
					try {
						hour = Integer.parseInt(tok[0]);
						minute = Integer.parseInt(tok[1]);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}else if(key.equals("game")) {
					game = jo.getString(key);
				}else if(key.equals("style")) {
					style = jo.getString(key);
				}else if(key.equals("location")) {
					location = jo.getString(key);
				}else if(key.equals("locked")) {
					locked = jo.getInt(key);
				}
			}

			calendar.set(year, month, days, hour, minute, 0);
			date = calendar.getTime();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public int getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	@Override
	public String toString() {
		return "TournamentAlpha [tournament_id=" + tournament_id + ", name=" + name + ", organization=" + organization
				+ ", date=" + date + ", game=" + game + ", style=" + style + ", location=" + location + "]";
	}

	public String toJSON() {
		String json = "{";
		int items = 0;
		
		if(tournament_id > 0) {
			json += "\"tournament_id\":\"" + tournament_id + "\"";
			items++;
		}
		if(name != null) {
			if(items > 0) json += ",";
			json += "\"name\":\"" + name + "\"";
			items++;
		}
		if(organization != null) {
			if(items > 0) json += ",";
			json += "\"organization\":\"" + organization + "\"";
			items++;
		}
		if(date != null) {
			if(items > 0) json += ",";
			int year = 0, month = 0, day = 0, hour = 0, minute = 0;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DATE);
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			
			json += "\"date\":\"" + year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day) + "\"";
			items++;
			
			json += ",\"time\":\"" + String.format("%02d", hour) + ":" + String.format("%02d", minute) + "\"";
			items++;
		}
		if(game != null) {
			if(items > 0) json += ",";
			json += "\"game\":\"" + game + "\"";
			items++;
		}
		if(style != null) {
			if(items > 0) json += ",";
			json += "\"style\":\"" + style + "\"";
			items++;
		}
		if(location != null) {
			if(items > 0) json += ",";
			json += "\"location\":\"" + location + "\"";
			items++;
		}
		if(items > 0) json += ",";
		if(locked > 0) {
			json += "\"locked\":\"true\"";
		}else{
			json += "\"locked\":\"false\"";
		}
		items++;
		json += "}";

		return json;
	}
}
