package objects;

import org.json.JSONObject;

import interfaces.ObjectInterface;
import utilities.JSONUtility;

public class Forum implements ObjectInterface {
	long id, parent;
	JSONObject permissions;
	String name;
	
	public Forum() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public JSONObject getPermissions() {
		return permissions;
	}
	
	public void setPermissions(JSONObject permissions) {
		this.permissions = permissions;
	}	
}
