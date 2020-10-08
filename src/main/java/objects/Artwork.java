package objects;

import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.History;
import interfaces.ObjectInterface;
import interfaces.Rateable;

public class Artwork implements ObjectInterface, Rateable, History{
	long id;
	JSONObject metadata, rating;
	JSONArray sale;
	String owner, file_type, title;
	Timestamp submitted;
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
		
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
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
	
	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
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

	@Override
	public JSONObject getHistory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHistory(JSONObject history) {
		// TODO Auto-generated method stub
		
	}
	
}

