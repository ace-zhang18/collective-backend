package objects;

import utilities.ObjectInterface;

public class ArtTag implements ObjectInterface{
	long art_tag_id, parent_id;
	String name;

	
	public ArtTag() {
		super();
	}
	

	public ArtTag(long parent_id, String name) {
		super();
		this.parent_id = parent_id;
		this.name = name;
	}



	public ArtTag(long art_tag_id, long parent_id, String name) {
		this(parent_id, name);
		this.art_tag_id = art_tag_id;
	}
	
	
	public long getParent_id() {
		return parent_id;
	}



	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void printClass() {
		System.out.println(this.getClass().getName());
	}



	@Override
	public long get_id() {
		// TODO Auto-generated method stub
		return art_tag_id;
	}



	@Override
	public void set_id(long id) {
		art_tag_id = id;
	}

}

