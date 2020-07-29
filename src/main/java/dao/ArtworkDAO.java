package dao;

import java.util.List;
import objects.Artwork;
import utilities.DAOBase;

public class ArtworkDAO extends DAOBase{
	private static ArtworkDAO dao_instance;
	
	private ArtworkDAO() {
		
	}
	
	public Artwork getByTitle(String title){
		return SqlSessionContainer.getSession().selectOne("Artwork.getByTitle", title);
	}
	
	public static ArtworkDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ArtworkDAO();
		}
		return dao_instance;
	}
}
