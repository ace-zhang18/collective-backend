package dao;

import java.util.List;

import objects.ArtTag;
import objects.Artwork;
import utilities.DAOBase;

public class ArtTagDAO extends DAOBase{
	private static ArtTagDAO dao_instance;
	
	private ArtTagDAO() {
		
	}
	
	public ArtTag getByName(String name){
		return SqlSessionContainer.getSession().selectOne("ArtTag.getByName", name);
	}
	
	public static ArtTagDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new ArtTagDAO();
		}
		return dao_instance;
	}
}
