package utilities;

import org.json.JSONObject;

public interface ObjectInterface {	
	public default JSONObject toJSON() {
		return new JSONObject(this);
	}

	public long get_id();
	
	public void set_id(long id);
}
