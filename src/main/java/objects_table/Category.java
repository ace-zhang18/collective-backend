package objects_table;

import java.sql.Timestamp;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.ObjectInterface;

public class Category implements ObjectInterface{
	long id, cover, symbol;
	String name;
	
	public Category() {
		super();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCover() {
		return cover;
	}

	public void setCover(long cover) {
		this.cover = cover;
	}
	
	public long getSymbol() {
		return symbol;
	}

	public void setSymbol(long symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

