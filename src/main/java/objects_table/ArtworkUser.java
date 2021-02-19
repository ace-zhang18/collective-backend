package objects_table;

import interfaces.ObjectInterface;

public class ArtworkUser implements ObjectInterface{
	long artwork, user;
	
	public ArtworkUser() {
		
	}

	public long getArtwork() {
		return artwork;
	}

	public void setArtwork(long artwork) {
		this.artwork = artwork;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
}
