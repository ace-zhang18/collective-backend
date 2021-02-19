package dao;

import interfaces.DAOBase;

public class AnnouncementDAO extends DAOBase{
	private static AnnouncementDAO dao_instance = null;
	
	private AnnouncementDAO() {
		
	}
	
	public static AnnouncementDAO getInstance() {
		if (dao_instance == null) {
			dao_instance = new AnnouncementDAO();
		}
		return dao_instance;
	}
}
