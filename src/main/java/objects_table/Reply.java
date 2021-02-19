package objects_table;

import java.sql.Timestamp;

import interfaces.ObjectInterface;

public class Reply implements ObjectInterface{
	long id, source_id, author;
	String source, content;
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
	public long getSource_id() {
		return source_id;
	}
	public void setSource_id(long source_id) {
		this.source_id = source_id;
	}
	public long getAuthor() {
		return author;
	}
	public void setAuthor(long author) {
		this.author = author;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source.toLowerCase();
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
