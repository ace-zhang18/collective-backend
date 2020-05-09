package objects;

import java.sql.Timestamp;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONInterface;
import utilities.JSONUtility;

public class Post implements JSONInterface {
	long post_id, thread, author;
	Timestamp timestamp;
	String title, text;
	PGobject rating, history;
	
	public Post() {
		super();
	}
	
	public Post(long post_id, long thread, long author, Timestamp timestamp, String title, String text,
			PGobject rating, PGobject history) {
		this(thread, author, timestamp, title, text, rating, history);
		this.post_id = post_id;
	}

	public Post(long thread, long author, Timestamp timestamp, String title, String text, PGobject rating, PGobject history) {
		super();
		this.thread = thread;
		this.author = author;
		this.timestamp = timestamp;
		this.title = title;
		this.text = text;
		this.rating = rating;
		this.history = history;
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public long getThread() {
		return thread;
	}

	public void setThread(long thread) {
		this.thread = thread;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public PGobject getRating() {
		return rating;
	}

	public JSONObject getRatingAsJSONObject() {
		return JSONUtility.PGtoJSON(rating);
	}

	public void setRating(PGobject rating) {
		this.rating = rating;
	}
	
	public void setRating(JSONObject rating) {
		this.rating = JSONUtility.JSONtoPG(rating);
	}
	
	public PGobject getHistory() {
		return history;
	}

	public JSONObject getHistoryAsJSONObject() {
		return JSONUtility.PGtoJSON(history);
	}
	
	public void setHistory(PGobject history) {
		this.history = history;
	}
	
	public void setHistory(JSONObject history) {
		this.history = JSONUtility.JSONtoPG(history);
	}
	
}
