package dao;

import java.util.List;

import interfaces.DAOBase;
import objects.*;

public class GalleryDAO extends DAOBase{
	private static GalleryDAO dao_instance;
	
	private GalleryDAO() {
		
	}
	
	public List<Gallery> getSub(int forum_id){
		return SqlSessionContainer.getSession().selectList("Gallery.getSub", forum_id);		
	}
	
	public static GalleryDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new GalleryDAO();
		}
		return dao_instance;
	}
}
