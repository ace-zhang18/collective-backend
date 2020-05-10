package objects;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONInterface;
import utilities.JSONUtility;

public class Forum implements JSONInterface {
	long forum_id, parent;
	PGobject permissions;
	String name;
	
	public Forum() {
		super();
	}
	
	public Forum(long forum_id, String name, long parent, PGobject permissions) {
		this(name, parent, permissions);
		this.forum_id = forum_id;
	}

	public Forum(String name, long parent, PGobject permissions) {
		super();
		this.name = name;
		this.parent = parent;
		this.permissions = permissions;
	}

	public long getForum_id() {
		return forum_id;
	}

	public void setForum_id(long forum_id) {
		this.forum_id = forum_id;
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
		return "Forum [forum_id=" + forum_id + ", name=" + name + ", parent=" + parent + ", permissions=" + permissions 
				+ "]";
	}
}
