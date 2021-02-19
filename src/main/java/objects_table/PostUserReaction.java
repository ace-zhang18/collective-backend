package objects_table;

import interfaces.ObjectInterface;

public class PostUserReaction implements ObjectInterface{
	long post_id, user, reaction;
	String post_source;

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getReaction() {
		return reaction;
	}

	public void setReaction(long reaction) {
		this.reaction = reaction;
	}

	public String getPost_source() {
		return post_source;
	}

	public void setPost_source(String post_source) {
		this.post_source = post_source.toLowerCase();
	}
}
