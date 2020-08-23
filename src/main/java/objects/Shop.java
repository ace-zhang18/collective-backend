package objects;

import org.json.JSONObject;

import interfaces.ObjectInterface;

public class Shop implements ObjectInterface{
	long id, forum;
	JSONObject permissions;
	String Display;
	
	public Shop() {
		
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
}
