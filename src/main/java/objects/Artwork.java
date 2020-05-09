package objects;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONUtility;

public class Artwork {
	long artwork_id;
	PGobject owner, permissions, history;
	PGobject[] sale;
	String type, content;
	boolean nsfw;
	
	public Artwork() {
		super();
	}
	
	public Artwork(long artwork_id, PGobject owner, PGobject history, PGobject[] sale, String type, String content, boolean nsfw) {
		this(owner, history, sale, type, content, nsfw);
		this.artwork_id = artwork_id;
	}
	
	public Artwork(PGobject owner, PGobject history, PGobject[] sale, String type, String content, boolean nsfw) {
		super();
		this.owner = owner;
		this.history = history;
		this.sale = sale;
		this.type = type;
		this.content = content;
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
	
	public PGobject getHistory() {
		return history;
	}

	public JSONObject getHistoryAsJSONObject() {
		return JSONUtility.PGtoJSON(history);
	}
	
	public void setHistory(PGobject history) {
		this.history = history;
	}
	
	public void setHistory(JSONObject history) {
		this.history = JSONUtility.JSONtoPG(history);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isNsfw() {
		return nsfw;
	}

	public void setNsfw(boolean nsfw) {
		this.nsfw = nsfw;
	}
	
}

