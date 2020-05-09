package objects;

import java.sql.Timestamp;
import java.util.Arrays;

import org.json.JSONObject;
import org.postgresql.util.PGobject;

import utilities.JSONUtility;

public class Gallery {
	long gallery_id;
	long[] collection;
	PGobject permissions, history;
	PGobject[] sale;
	boolean nsfw;
	
	public Gallery(long gallery_id, long[] collection, PGobject permissions, PGobject[] sale, boolean nsfw) {
		this(collection, permissions, sale, nsfw);
		this.gallery_id = gallery_id;
	}
	
	public Gallery(long[] collection, PGobject permissions, PGobject[] sale, boolean nsfw) {
		super();
		this.collection = collection;
		this.permissions = permissions;
		this.sale = sale;
		this.nsfw = nsfw;
	}

	public long getGallery_id() {
		return gallery_id;
	}

	public void setGallery_id(long gallery_id) {
		this.gallery_id = gallery_id;
	}

	public long[] getCollection() {
		return collection;
	}

	public void setCollection(long[] collection) {
		this.collection = collection;
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

	public boolean isNsfw() {
		return nsfw;
	}

	public void setNsfw(boolean nsfw) {
		this.nsfw = nsfw;
	}
	
	
}

