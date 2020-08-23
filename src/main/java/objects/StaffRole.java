package objects;

import java.sql.SQLException;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONUtility;

public class StaffRole {
	long id;
	String name;
	JSONObject permissions;
	
	public StaffRole() {
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

	public JSONObject getPermissions() {
		return permissions;
	}

	public void setPermissions(JSONObject permissions) {
		this.permissions = permissions;
	}
	
	
	
}
