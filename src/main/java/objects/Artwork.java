package objects;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONUtility;

public class Artwork {
	long artwork_id;
	PGobject owner, permissions;
	PGobject[] sale;
	String type, art;
	boolean nsfw;
	
	public Artwork() {
		super();
	}
	
	public Artwork(long artwork_id, PGobject owner, PGobject[] sale, String type, String art, boolean nsfw) {
		this(owner, sale, type, art, nsfw);
		this.artwork_id = artwork_id;
	}
	
	public Artwork(PGobject owner, PGobject[] sale, String type, String art, boolean nsfw) {
		super();
		this.owner = owner;
		this.sale = sale;
		this.type = type;
		this.art = art;
		this.nsfw = nsfw;
	}

	public long getArtwork_id() {
		return artwork_id;
	}

	public void setArtwork_id(long artwork_id) {
		this.artwork_id = artwork_id;
	}

	public PGobject getOwner() {
		return owner;
	}
	
	public JSONObject getOwnerAsJSONObject() {
		return JSONUtility.PGtoJSON(owner);
	}
		
	public void setOwner(PGobject owner) {
		this.owner = owner;
	}
	
	public void setOwner(JSONObject owner) {
		this.owner = JSONUtility.JSONtoPG(owner);
	}

	public PGobject[] getSale() {
		return sale;
	}

	public JSONObject[] getSaleAsJSONObject() {
		return JSONUtility.PGtoJSON(sale);
	}
	
	public void setSale(PGobject[] sale) {
		this.sale = sale;
	}
	
	public void setSale(JSONObject[] sale) {
		this.sale = JSONUtility.JSONtoPG(sale);
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public boolean isNsfw() {
		return nsfw;
	}

	public void setNsfw(boolean nsfw) {
		this.nsfw = nsfw;
	}
	
}

