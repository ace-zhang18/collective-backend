package objects;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.History;
import interfaces.ObjectInterface;
import interfaces.Rateable;
import utilities.JSONUtility;

public class Gallery implements ObjectInterface, Rateable, History {
	long id, cover;
	String title;
	long[] collection, tags;
	JSONObject permissions, history, owners, rating;
	JSONArray sale;

	public Gallery() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCover() {
		return cover;
	}

	public void setCover(long cover) {
		this.cover = cover;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long[] getCollection() {
		return collection;
	}

	public void setCollection(long[] collection) {
		this.collection = collection;
	}

	public long[] getTags() {
		return tags;
	}

	public void setTags(long[] tags) {
		this.tags = tags;
	}

	public JSONObject getPermissions() {
		return permissions;
	}
	
	public void setPermissions(JSONObject permissions) {
		this.permissions = permissions;
	}

	public JSONObject getHistory() {
		return history;
	}

	public void setHistory(JSONObject history) {
		this.history = history;
	}

	public JSONObject getOwners() {
		return owners;
	}

	public void setOwners(JSONObject owners) {
		this.owners = owners;
	}

	public JSONArray getSale() {
		return sale;
	}
	
	public void setSale(JSONArray sale) {
		this.sale = sale;
	}

	public JSONObject getRating() {
		return rating;
	}

	public void setRating(JSONObject rating) {
		this.rating = rating;		
	}	
}

