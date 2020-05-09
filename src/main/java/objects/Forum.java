package objects;

import java.sql.Timestamp;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONInterface;
import utilities.JSONUtility;

public class Forum implements JSONInterface {
	long thread_id, forum, author;
	PGobject permissions;
	String title;
	Timestamp timestamp;
	
	public Forum() {
		super();
	}
	
	public Forum(long thread_id, long forum, long author, PGobject permissions, String title, Timestamp timestamp) {
		this(forum, author, permissions, title, timestamp);
		this.thread_id = thread_id;
	}
	
	public Forum(long forum, long author, PGobject permissions, String title, Timestamp timestamp) {
		super();
		this.forum = forum;
		this.author = author;
		this.permissions = permissions;
		this.title = title;
		this.timestamp = timestamp;
	}

	public long getThread_id() {
		return thread_id;
	}

	public void setThread_id(long thread_id) {
		this.thread_id = thread_id;
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

	public PGobject getPermissions() {
		return permissions;
	}
	
	public JSONObject getPermissionsAsJSONObject() {
		return JSONUtility.PGtoJSON(permissions);
	}

	public void setPermissions(PGobject permissions) {
		this.permissions = permissions;
	}
	
	public void setPermissions(JSONObject permissions) {
		this.permissions = JSONUtility.JSONtoPG(permissions);
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
	
}
