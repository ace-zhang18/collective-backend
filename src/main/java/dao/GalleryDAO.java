package dao;

import java.util.List;

import objects.*;
import utilities.DAOBase;

public class GalleryDAO extends DAOBase{
	private static GalleryDAO dao_instance;
	
	private GalleryDAO() {
		
	}
	
	public static List<Gallery> getSub(int forum_id){
		return SqlSessionContainer.getSession().selectList("Gallery.getSub", forum_id);		
	}
	
	public static GalleryDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new GalleryDAO();
		}
		return dao_instance;
	}
}
