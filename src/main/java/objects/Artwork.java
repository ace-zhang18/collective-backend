package objects;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.History;
import interfaces.ObjectInterface;
import interfaces.Rateable;

public class Artwork implements ObjectInterface, Rateable, History{
	long id;
	JSONObject owners, permissions, history, metadata, rating;
	JSONArray sale;
	String file_type, title;
	long[] tags;
	
	public Artwork() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	public JSONObject getOwners() {
		return owners;
	}
	
	public void setOwners(JSONObject owner) {
		this.owners = owner;
	}
	
	public JSONObject getMetadata() {
		return metadata;
	}

	public void setMetadata(JSONObject metadata) {
		this.metadata = metadata;
	}
	
	public JSONArray getSale() {
		return sale;
	}

	public void setSale(JSONArray sale) {
		this.sale = sale;
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

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long[] getTags() {
		return tags;
	}

	public void setTags(long[] tags) {
		this.tags = tags;
	}
	
	public void printClass() {
		System.out.println(this.getClass().getName());
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

