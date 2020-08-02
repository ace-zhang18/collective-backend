package objects;

import utilities.ObjectInterface;

public class ArtTag implements ObjectInterface{
	long tag_id;
	long parent;
	String name;

	
	public ArtTag() {
		super();
	}
	

	public ArtTag(long parent, String name) {
		super();
		this.parent = parent;
		this.name = name;
	}



	public ArtTag(long art_tag_id, long parent, String name) {
		this(parent, name);
		this.tag_id = art_tag_id;
	}
	
	
	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
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
		return tag_id;
	}



	@Override
	public void set_id(long id) {
		tag_id = id;
	}

}

