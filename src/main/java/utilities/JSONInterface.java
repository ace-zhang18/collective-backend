package utilities;

import org.json.JSONObject;

public interface JSONInterface {
	public default JSONObject toJSON() {
		return new JSONObject(this);
	}
}
