package objects;

import java.sql.Timestamp;

import org.json.JSONObject;

import interfaces.ObjectInterface;
import interfaces.Rateable;
import utilities.JSONUtility;

public class Thread implements ObjectInterface, Rateable {
	long id, forum, author;
	JSONObject permissions, rating;
	String title;
	Timestamp timestamp;
	
	public Thread() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getForum() {
		return forum;
	}

	public void setForum(long forum) {
		this.forum = forum;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public JSONObject getPermissions() {
		return permissions;
	}
		
	public void setPermissions(JSONObject permissions) {
		this.permissions = permissions;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public JSONObject getRating() {
		return rating;
	}

	@Override
	public void setRating(JSONObject rating) {
		this.rating = rating;
	}
}
