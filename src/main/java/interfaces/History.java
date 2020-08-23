package interfaces;

import org.json.JSONObject;

public interface History {
	public abstract JSONObject getHistory();
	
	public abstract void setHistory(JSONObject history);
}
