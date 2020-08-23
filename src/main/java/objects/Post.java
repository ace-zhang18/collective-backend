package objects;

import java.sql.Timestamp;

import org.json.JSONObject;

import interfaces.History;
import interfaces.ObjectInterface;
import interfaces.Rateable;
import utilities.JSONUtility;

public class Post implements ObjectInterface, Rateable, History {
	long id, thread, author;
	Timestamp timestamp;
	String title, text;
	JSONObject rating, history;
	
	public Post() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public JSONObject getHistory() {
		return history;
	}

	public void setHistory(JSONObject history) {
		this.history = history;
	}
	
	public JSONObject getRating() {
		return rating;
	}

	public void setRating(JSONObject rating) {
		this.rating = rating;		
	}	
	
}
