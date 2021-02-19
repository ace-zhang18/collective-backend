package objects_table;

import org.json.JSONObject;

import interfaces.ObjectInterface;

public class Shop implements ObjectInterface{
	long id, category, cover;
	String[] tags;
	
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

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public long getCover() {
		return cover;
	}

	public void setCover(long cover) {
		this.cover = cover;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}


	
	
	
}
