package objects_table;

import java.sql.Timestamp;

import interfaces.ObjectInterface;

public class Status implements ObjectInterface{
	long id, author;
	String content;
	Timestamp timestamp;
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
	public long getAuthor() {
		return author;
	}
	public void setAuthor(long author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
