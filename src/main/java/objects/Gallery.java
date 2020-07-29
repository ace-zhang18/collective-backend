package objects;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.ObjectInterface;
import utilities.JSONUtility;

public class Gallery implements ObjectInterface {
	long gallery_id, cover;
	String title;
	long[] collection, tags;
	PGobject permissions, history, owners;
	PGobject[] sale;

	public Gallery() {
		super();
	}
	
	public Gallery(	long gallery_id, long cover, String title, long[] collection, long[] tags,
			PGobject permissions, PGobject history, PGobject owners, PGobject[] sale) {
		this(cover, title, collection, tags, permissions, history, owners, sale);
		this.gallery_id = gallery_id;
	}

	public Gallery(	long cover, String title, long[] collection, long[] tags,
			PGobject permissions, PGobject history, PGobject owners, PGobject[] sale) {
		super();
		this.cover = cover;
		this.title = title;
		this.collection = collection;
		this.tags = tags;
		this.permissions = permissions;
		this.history = history;
		this.owners = owners;
		this.sale = sale;
	}

	public long get_id() {
		return gallery_id;
	}

	public void set_id(long gallery_id) {
		this.gallery_id = gallery_id;
	}

	public long getCover() {
		return cover;
	}

	public void setCover(long cover) {
		this.cover = cover;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long[] getCollection() {
		return collection;
	}

	public void setCollection(long[] collection) {
		this.collection = collection;
	}

	public long[] getTags() {
		return tags;
	}

	public void setTags(long[] tags) {
		this.tags = tags;
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

	public PGobject getHistory() {
		return history;
	}
	
	public JSONObject getHistoryAsJSONObject() {
		return JSONUtility.PGtoJSON(history);
	}

	public void setHistory(PGobject history) {
		this.history = history;
	}

	public PGobject getOwners() {
		return owners;
	}
	
	public JSONObject getOwnersAsJSONObject() {
		return JSONUtility.PGtoJSON(owners);
	}

	public void setOwners(PGobject owners) {
		this.owners = owners;
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

	
}

