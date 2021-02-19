package objects_table;

import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.ObjectInterface;

public class Artwork implements ObjectInterface{
	long id, submitter, category;
	String file_type, title, url;
	Timestamp timestamp;
	
	public Artwork() {
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getSubmitter() {
		return submitter;
	}
	
	public void setSubmitter(long submitter) {
		this.submitter = submitter;
	}
	
	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
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
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}


