package dao;

import java.util.List;
import objects.Artwork;
import utilities.DAOBase;

public class ArtTagDAO extends DAOBase{
	private static ArtTagDAO dao_instance;
	
	private ArtTagDAO() {
		
	}
	
	public Artwork getByTitle(String title){
		return SqlSessionContainer.getSession().selectOne("Artwork.getByTitle", title);
	}
	
	public static ArtTagDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ArtTagDAO();
		}
		return dao_instance;
	}
}
