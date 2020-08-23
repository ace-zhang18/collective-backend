package objects;

import interfaces.ObjectInterface;

public class ArtTag implements ObjectInterface{
	long id;
	long parent;
	String name;

	
	public ArtTag() {
		super();
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
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}



	@Override
	public void setId(long id) {
		this.id = id;
	}

}

