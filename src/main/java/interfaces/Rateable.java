package interfaces;

import org.json.JSONObject;

public interface Rateable {	
	public abstract JSONObject getRating();
	
	public abstract void setRating(JSONObject rating);
	
	public default int getTotalRating() {
		JSONObject rating = getRating();
		int upvotes = rating.getJSONArray("upvotes").length();
		int downvotes = rating.getJSONArray("downvotes").length();
		
		return upvotes-downvotes;
	}
}
