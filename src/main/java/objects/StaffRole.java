package objects;

import java.sql.SQLException;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONUtility;

public class StaffRole {
	long role_id;
	String name;
	PGobject permissions;
	
	public StaffRole() {
		super();
	}
	
	public StaffRole(String name, String permissions) {
		super();
		this.name = name;
		this.permissions = new PGobject();
		this.permissions.setType("json");
		try {
			this.permissions.setValue(permissions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public StaffRole(String name, PGobject permissions) {
		super();
		this.name = name;
		this.permissions = permissions;
	}

	public StaffRole(long id, String name, PGobject permissions) {
		this(name, permissions);
		this.role_id = id;
	}

	public long getId() {
		return role_id;
	}

	public void setId(long id) {
		this.role_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "StaffRole [id=" + role_id + ", name=" + name + ", permissions=" + permissions + "]";
	}
	
	
}
