package objects;

public class LatLng {
	double lat, lng;
	
	public LatLng() {
		super();
	}

	public LatLng(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "LatLng [lat=" + lat + ", lng=" + lng + "]";
	}
	
	public String toJSON() {
		String JSON = "{";
		JSON += "\"lat\":\"" + lat + "\"";
		JSON += ",\"lng\":\"" + lng + "\"";
		JSON += "}";
		
		return JSON;
	}
	
}
