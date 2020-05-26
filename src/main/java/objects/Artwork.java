package objects;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONInterface;
import utilities.JSONUtility;

public class Artwork implements JSONInterface{
	long artwork_id;
	PGobject owner, permissions, history, metadata;
	PGobject[] sale;
	String file_type, title;
	long[] tags;
	
	public Artwork() {
		super();
	}
	
	public Artwork(long artwork_id, PGobject owner, PGobject permissions, PGobject history, PGobject metadata, PGobject[] sale, String file_type, String title, long[] tags) {
		this(owner, permissions, history, metadata, sale, file_type, title, tags);
		this.artwork_id = artwork_id;
	}
	
	public Artwork(PGobject owner, PGobject permissions, PGobject history, PGobject metadata, PGobject[] sale, String file_type, String title, long[] tags) {
		super();
		this.owner = owner;
		this.permissions = permissions;
		this.history = history;
		this.metadata = metadata;
		this.sale = sale;
		this.file_type = file_type;
		this.title = title;
		this.tags = tags;
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
	
	public PGobject getMetadata() {
		return metadata;
	}
	
	public JSONObject getMetadataAsJSONObject() {
		return JSONUtility.PGtoJSON(metadata);
	}

	public void setMetadata(PGobject metadata) {
		this.metadata = metadata;
	}

	public void setMetadata(JSONObject metadata) {
		this.metadata = JSONUtility.JSONtoPG(metadata);
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
	
}

